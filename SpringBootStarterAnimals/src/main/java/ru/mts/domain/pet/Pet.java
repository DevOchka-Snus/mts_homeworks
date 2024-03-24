package ru.mts.domain.pet;

import ru.mts.domain.AbstractAnimal;
import ru.mts.domain.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Pet extends AbstractAnimal {

    public Pet(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(animalType, breed, name, cost, character, birthDate);
    }

}
