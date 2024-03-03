package ru.mts.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import ru.mts.domain.Animal;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


public class AnimalRepositoryImpl implements AnimalRepository {

    private final ObjectProvider<CreateAnimalService> createAnimalService;

    private final Map<String, List<Animal>> animals = new HashMap<>();

    private boolean initialized;

    public AnimalRepositoryImpl(ObjectProvider<CreateAnimalService> createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void postConstruct() {
        if (!initialized) {

            var prototype = createAnimalService.getIfAvailable();

            if (!Objects.nonNull(prototype)) {
                throw new RuntimeException("huyna kakaya-to");
            }

            var animalMap = prototype.createAnimals();
            if (Objects.nonNull(animalMap)) {
                for (var pair : animalMap.entrySet()) {
                    animals.computeIfAbsent(pair.getKey(), it -> new ArrayList<>()).addAll(pair.getValue());
                }
            }

            initialized = true;
        }

    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animals.entrySet().stream()
                .flatMap(animal -> animal.getValue().stream()
                        .filter(it -> Objects.nonNull(it.getBirthDate()) && it.getBirthDate().isLeapYear())
                        .map(it -> Map.entry(animal.getKey() + it.getName(), it.getBirthDate())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        if (n <= 0) {
            throw new IllegalStateException("number must be more than 0");
        }
        Map<Animal, Integer> result = new HashMap<>();


        final var now = LocalDate.now();

        return animals.entrySet().stream()
                .flatMap(animal -> animal.getValue().stream()
                        .filter(it -> Objects.nonNull(it.getBirthDate()) && it.getBirthDate().plusYears(n).isBefore(now))
                        .map(it -> Map.entry(it, (int) ChronoUnit.YEARS.between(it.getBirthDate(), now))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a));
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {

        return animals.entrySet().stream()
                .flatMap(animal -> animal.getValue().stream()
                        .collect(Collectors.groupingBy(it -> it, Collectors.counting()))
                        .entrySet().stream()
                        .filter(entry -> entry.getValue() > 1)
                        .map(Map.Entry::getKey)
                )
                .collect(Collectors.groupingBy(Animal::getType, Collectors.toList()));
    }

    @Override
    public int findAverageAge(List<Animal> animalList) {
        var amount = new BigDecimal(animalList.size());
        var sum = animalList.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(amount).setScale(0, RoundingMode.HALF_UP).intValue();
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalList) {
        var now = LocalDate.now();
        var averageCost = new BigDecimal(findAverageAge(animalList));
        return animalList.stream()
                .filter(it -> ChronoUnit.YEARS.between(it.getBirthDate(), now) > 5
                        && it.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparingInt(a -> (int) ChronoUnit.YEARS.between(a.getBirthDate(), now)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Animal> findMinConstAnimals(List<Animal> animalList) {
        return animalList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .sorted(Comparator.comparing(Animal::getName))
                .collect(Collectors.toList());
    }

}
