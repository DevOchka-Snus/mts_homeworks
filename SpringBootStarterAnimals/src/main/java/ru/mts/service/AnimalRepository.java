package ru.mts.service;

import ru.mts.domain.Animal;

import java.time.LocalDate;
import java.util.Map;

/**
 * The SearchService interface contains methods
 * that filter array of Animal objects
 *
 * @author vladi
 * @version 1.0
 */
public interface AnimalRepository {

    String NAME = "mts_AnimalRepository";

    /**
     * Method filters array of Animal objects based on
     * whether the year of birth of the Animal object is a leap year
     *
     * @return filtered array of Animal objects
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Method filters array of Animal objects based on
     * whether Animal object is older than n years
     *
     * @param n is age for filter
     * @return filtered array of Animal objects
     */
    Map<Animal, Integer> findOlderAnimal(int n);

    /**
     * Method filters array of Animal objects based on
     * whether Animal object is duplicate
     *
     * @return map of Animal duplicates
     */
    Map<String, Integer> findDuplicate();
}
