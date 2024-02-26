package ru.mts.service.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import ru.mts.domain.Animal;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.*;

public class AnimalRepositoryImpl implements AnimalRepository {

    private final ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    private final Animal[] animals;

    private boolean initialized;

    {
        animals = new Animal[(Integer.MAX_VALUE / 100_000)];
    }

    public AnimalRepositoryImpl(ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        this.createAnimalServicesBeanProvider = createAnimalServicesBeanProvider;
    }

    @PostConstruct
    public void postConstruct() {
        if (!initialized) {

            Animal[] temp;
            CreateAnimalService prototype;
            for (int i = 0; i < animals.length; i++) {
                prototype = createAnimalServicesBeanProvider.getIfAvailable();
                if (Objects.isNull(prototype)) {
                    throw new RuntimeException("Caramba! 'prototype' is null");
                }

                temp = prototype.createAnimals();
                if (Objects.nonNull(temp)) {
                    animals[i] = temp[temp.length / 2];
                }

            }

            initialized = true;
        }

    }

    @Override
    public Animal[] findLeapYearNames() {
        return Arrays.stream(animals)
                .filter(Objects::nonNull)
                .filter(it -> Objects.nonNull(it.getBirthDate())
                        && it.getBirthDate().isLeapYear())
                .toArray(Animal[]::new);
    }

    @Override
    public Animal[] findOlderAnimal(int n) {
        if (n <= 0) {
            throw new IllegalStateException("number must be more than 0");
        }

        final var now = LocalDate.now();

        return Arrays.stream(animals)
                .filter(Objects::nonNull)
                .filter(it -> Objects.nonNull(it.getBirthDate())
                        && it.getBirthDate().plusYears(n).isBefore(now))
                .toArray(Animal[]::new);
    }

    @Override
    public Animal[] findDuplicate() {
        Set<Animal> set = new HashSet<>();
        List<Animal> result = new ArrayList<>();
        for (Animal a : animals) {
            if (Objects.nonNull(a)) {
                if (set.contains(a)) {
                    result.add(a);
                } else {
                    set.add(a);
                }
            }

        }

        return result.toArray(Animal[]::new);
    }

}
