package ru.mts.domain.factory;

import ru.mts.domain.Animal;
import ru.mts.domain.pet.Cat;
import ru.mts.domain.pet.Dog;
import ru.mts.domain.pet.Pet;
import ru.mts.domain.predator.Lion;
import ru.mts.domain.predator.Predator;
import ru.mts.domain.predator.Wolf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
