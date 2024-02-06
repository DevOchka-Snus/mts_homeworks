package ru.mts.service;

import ru.mts.domain.Animal;

/**
 * The CreateAnimalService interface contains methods
 * that create Animal objects
 *
 * @author vladi
 * @version 1.0
 */
public interface CreateAnimalService {
    /**
     * Method create array of Animal objects
     *
     * @return array of Animal objects
     */
    Animal[] createAnimals();
}