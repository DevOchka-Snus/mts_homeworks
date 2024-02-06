package ru.mts.domain.factory;

import ru.mts.domain.Animal;

/**
 * AnimalFactory class create Animal objects
 *
 * @version 1.0
 * @author vladi
 * */
public abstract class AnimalFactory {

    /**
     * Public abstract method create Animal object
     *
     * @return Animal object
     * */
    public abstract Animal createAnimal();

}
