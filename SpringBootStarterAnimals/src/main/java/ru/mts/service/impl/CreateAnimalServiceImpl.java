package ru.mts.service.impl;

import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.factory.AnimalFactory;
import ru.mts.service.CreateAnimalService;

import java.util.*;

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
    public Map<String, List<Animal>> createAnimals() {
        int count = 10;

        Map<String, List<Animal>> animalMap = new HashMap<>();

        for (var factory : animalFactories.entrySet()) {
            animalMap.put(factory.getKey(), new ArrayList<>());
            for (int i = 0; i < count; i++) {
                animalMap.get(factory.getKey()).add(factory.getValue().createAnimal());
            }
        }

        return animalMap;
    }

}