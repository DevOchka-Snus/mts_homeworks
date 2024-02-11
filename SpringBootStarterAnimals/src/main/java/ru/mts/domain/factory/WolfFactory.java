package ru.mts.domain.factory;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.domain.Animal;
import ru.mts.domain.predator.Wolf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WolfFactory extends AnimalFactory {
    @Value("${animal.wolf.names}")
    private String[] wolfNames;

    @Override
    public Animal createAnimal() {
        var name = wolfNames[(int) System.currentTimeMillis() % wolfNames.length];
        return new Wolf("wolf " + System.currentTimeMillis(),
                name + " " + System.currentTimeMillis(),
                new BigDecimal(System.currentTimeMillis() % 1000007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("22-02-1003", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}
