package ru.mts.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.mts.domain.Animal;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.*;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {
    private final CreateAnimalService createAnimalService;

    private Animal[] animals;

    public AnimalRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    private void fillAnimals() {
        animals = createAnimalService.createAnimals();
    }
    @Override
    public Animal[] findLeapYearNames() {
        return Arrays.stream(animals).filter((it) -> it.getBirthDate().isLeapYear()).toArray(Animal[]::new);
    }

    @Override
    public Animal[] findOlderAnimal(int n) {
        if (n <= 0) {
            throw new IllegalStateException("number must be more than 0");
        }
        return Arrays.stream(animals).filter((it) -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now())).toArray(Animal[]::new);
    }

    @Override
    public Animal[] findDuplicate() {
        Set<Animal> set = new HashSet<>();
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (set.contains(animal)) {
                result.add(animal);
            } else {
                set.add(animal);
            }
        }
        return result.toArray(Animal[]::new);
    }
}
