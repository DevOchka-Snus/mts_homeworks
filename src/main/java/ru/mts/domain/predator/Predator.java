package ru.mts.domain.predator;

import ru.mts.domain.AbstractAnimal;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {
    public Predator(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }
}
