package ru.mts.service;

import ru.mts.domain.Animal;

import java.util.List;
import java.util.Map;

/**
 * The CreateAnimalService interface contains methods
 * that create Animal objects
 *
 * @author vladi
 * @version 1.0
 */
public interface CreateAnimalService {

    String NAME = "mts_CreateAnimalService";

    /**
     * Method create array of Animal objects
     *
     * @return map of Animal objects
     */
    Map<String, List<Animal>> createAnimals();

}