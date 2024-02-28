package ru.mts.domain.predator;

import ru.mts.domain.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Lion extends Predator {

    public Lion(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(AnimalType.LION, breed, name, cost, character, birthDate);
    }

}