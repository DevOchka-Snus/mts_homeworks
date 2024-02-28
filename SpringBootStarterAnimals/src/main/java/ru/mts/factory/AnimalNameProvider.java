package ru.mts.factory;

import ru.mts.domain.AnimalType;

/**
 * @author Vladislav Gruzdov
 */
public interface AnimalNameProvider {

    String NAME = "springBootStarterAnimals_AnimalNameProvider";

    String generateName(AnimalType animalType);

}
