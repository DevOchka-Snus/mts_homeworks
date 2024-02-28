package ru.mts.domain.pet;

import ru.mts.domain.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Dog extends Pet {

    public Dog(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(AnimalType.DOG, breed, name, cost, character, birthDate);
    }

}
