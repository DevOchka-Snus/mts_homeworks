package ru.mts.domain.pet;

import ru.mts.domain.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {

    public Cat(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(AnimalType.CAT, breed, name, cost, character, birthDate);
    }

}
