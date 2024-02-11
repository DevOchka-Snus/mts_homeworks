package ru.mts.domain.predator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Lion extends Predator {

    public Lion(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super("Lion", breed, name, cost, character, birthDate);
    }

}