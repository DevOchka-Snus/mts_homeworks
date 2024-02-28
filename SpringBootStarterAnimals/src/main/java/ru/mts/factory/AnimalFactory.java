package ru.mts.factory;

import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;

/**
 * AnimalFactory class create Animal objects
 *
 * @version 1.0
 * @author vladi
 * */
public interface AnimalFactory {

    /**
     * Public abstract method create Animal object
     *
     * @return Animal object
     * */
    Animal createAnimal();

    boolean isApplicable(AnimalType animalType);

}
