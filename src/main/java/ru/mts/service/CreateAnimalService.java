package ru.mts.service;

import ru.mts.domain.Animal;
import ru.mts.domain.AnimalFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The CreateAnimalService interface contains methods
 * that create Animal objects
 *
 * @author vladi
 * @version 1.0
 */
public interface CreateAnimalService {
    /**
     * Default method create array of Animal objects
     *
     * @return array of Animal objects
     */
    default Animal[] createAnimals() {
        List<Animal> animals = new ArrayList<>();
        AnimalFactory animalFactory = new AnimalFactory();
        var cost = new BigDecimal("100").setScale(2, RoundingMode.HALF_UP);
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                animals.add(animalFactory.createAnimal(i));
            } else {
                animals.add(animalFactory.createAnimal(i));
            }
        }
        return animals.toArray(Animal[]::new);
    }
}