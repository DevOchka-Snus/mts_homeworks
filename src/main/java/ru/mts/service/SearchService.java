package ru.mts.service;

import ru.mts.domain.Animal;

/**
 * The SearchService interface contains methods
 * that filter array of Animal objects
 *
 * @author vladi
 * @version 1.0
 */
public interface SearchService {

    /**
     * Method filters array of Animal objects based on
     * whether the year of birth of the Animal object is a leap year
     *
     * @param animals is array of Animal objects
     * @return filtered array of Animal objects
     */
    Animal[] findLeapYearNames(Animal[] animals);

    /**
     * Method filters array of Animal objects based on
     * whether Animal object is older than n years
     *
     * @param animals is array of Animal objects
     * @param n       is age for filter
     * @return filtered array of Animal objects
     */
    Animal[] findOlderAnimal(Animal[] animals, int n);

    /**
     * Method filters array of Animal objects based on
     * whether Animal object is duplicate
     *
     * @param animals is array of Animal objects
     * @return array of Animal duplicates
     */
    Animal[] findDuplicate(Animal[] animals);
}
