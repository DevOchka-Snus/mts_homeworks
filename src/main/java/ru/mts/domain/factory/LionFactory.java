package ru.mts.domain.factory;

import ru.mts.domain.Animal;
import ru.mts.domain.predator.Lion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LionFactory extends AnimalFactory {

    @Override
    public Animal createAnimal() {
        return new Lion("lion " + System.currentTimeMillis(),
                "Vanomas " + System.currentTimeMillis(),
                new BigDecimal(System.currentTimeMillis() % 1000007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("26-12-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}
