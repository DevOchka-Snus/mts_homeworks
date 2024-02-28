package ru.mts.domain.predator;

import ru.mts.domain.AbstractAnimal;
import ru.mts.domain.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {

    public Predator(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(animalType, breed, name, cost, character, birthDate);
    }

}
