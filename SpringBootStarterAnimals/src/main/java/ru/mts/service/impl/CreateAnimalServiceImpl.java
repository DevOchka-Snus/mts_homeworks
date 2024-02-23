package ru.mts.service.impl;

import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.factory.AnimalFactory;
import ru.mts.service.CreateAnimalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final Map<String, AnimalFactory> animalFactories;

    private AnimalType animalType;

    public CreateAnimalServiceImpl(Map<String, AnimalFactory> animalFactories) {
        this.animalFactories = Collections.unmodifiableMap(animalFactories);
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public Animal[] createAnimals() {
        int count = 10;
        List<Animal> animals = new ArrayList<>(count);

        AnimalFactory factory = animalFactories.values()
                .stream()
                .filter(f -> f.isApplicable(animalType))
                .findFirst()
                .orElseThrow();

        for (int i = 0; i < count; i++) {
            animals.add(factory.createAnimal());
        }

        return animals.toArray(Animal[]::new);
    }

}