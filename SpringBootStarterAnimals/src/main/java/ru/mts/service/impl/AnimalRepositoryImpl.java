package ru.mts.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import ru.mts.domain.Animal;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


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
        Map<String, LocalDate> result = new HashMap<>();
        for (var pair : animals.entrySet()) {
            var type = pair.getKey();
            for (var animal : pair.getValue()) {
                if (Objects.nonNull(animal.getBirthDate()) && animal.getBirthDate().isLeapYear()) {
                    result.put(type + animal.getName(), animal.getBirthDate());
                }
            }
        }
        return result;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int n) {
        if (n <= 0) {
            throw new IllegalStateException("number must be more than 0");
        }
        Map<Animal, Integer> result = new HashMap<>();


        final var now = LocalDate.now();

        for (var pair : animals.entrySet()) {
            for (var animal : pair.getValue()) {
                if (Objects.nonNull(animal.getBirthDate())
                        && animal.getBirthDate().plusYears(n).isBefore(now)) {
                    result.put(animal, (int) ChronoUnit.YEARS.between(animal.getBirthDate(), now));
                }
            }
        }

        return result;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        Set<Animal> set = new HashSet<>();
        Map<String, Integer> result = new HashMap<>();

        for (var pair : animals.entrySet()) {
            for (var animal : pair.getValue()) {
                if (set.contains(animal)) {
                    result.put(pair.getKey(), result.getOrDefault(animal, 0) + 1);
                } else {
                    set.add(animal);
                }
            }

        }

        return result;
    }

}
