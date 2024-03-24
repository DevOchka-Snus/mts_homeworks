package ru.mts.service;

import ru.mts.domain.Animal;
import ru.mts.exception.NullCollectionException;

import java.time.LocalDate;
import java.util.List;
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
    Map<String, List<Animal>> findDuplicate();

    /**
     * Method find average age of animals
     * @param animalList list of Animal objects
     *
     * @return average age of animals
     * */
    int findAverageAge(List<Animal> animalList) throws NullCollectionException;

    /**
     * Method find animals,
     * which age more than 5 and cost more than average cost
     *
     * @param animalList list of Animal objects
     *
     * @return list of sorted animals in increased order
     * */
    List<Animal> findOldAndExpensive(List<Animal> animalList) throws NullCollectionException;

    /**
     * Method find top 3 animals with the smallest cost
     *
     * @param animalList list of Animal objects
     *
     * @return list of top 3 animals in decreased order
     * */
    List<Animal> findMinConstAnimals(List<Animal> animalList) throws NullCollectionException;
}
