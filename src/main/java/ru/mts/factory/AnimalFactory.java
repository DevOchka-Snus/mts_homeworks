package ru.mts.factory;

import ru.mts.domain.Animal;

public interface AnimalFactory {

    /**
     * Public method create Animal object
     *
     * @param flag s value that defines the implementation of Animal
     * @return Animal object
     */
    Animal createAnimal(int flag);

}
