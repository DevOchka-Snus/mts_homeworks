package ru.mts.service;

import ru.mts.domain.Animal;

/**
 * The SearchService interface contains methods
 * that filter array of Animal objects
 * @version 1.0
 * @author vladi
 * */
public interface AnimalRepository {

    String NAME = "mts_AnimalRepository";

    /**
     * Method filters array of Animal objects based on
     * whether the year of birth of the Animal object is a leap year
     * @return filtered array of Animal objects
     * */
    Animal[] findLeapYearNames();
    /**
     * Method filters array of Animal objects based on
     * whether Animal object is older than n years
     * @param n is age for filter
     * @return filtered array of Animal objects
     * */
    Animal[] findOlderAnimal(int n);
    /**
     * Method filters array of Animal objects based on
     * whether Animal object is duplicate
     * @return array of Animal duplicates
     * */
    Animal[] findDuplicate();
}
