package ru.mts.domain.predator;

import ru.mts.domain.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {

    public Wolf(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(AnimalType.WOLF, breed, name, cost, character, birthDate);
    }

}