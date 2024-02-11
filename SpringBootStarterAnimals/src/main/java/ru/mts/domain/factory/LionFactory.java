package ru.mts.domain.factory;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.domain.Animal;
import ru.mts.domain.predator.Lion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LionFactory extends AnimalFactory {
    @Value("${animal.lion.names}")
    private String[] lionNames;

    @Override
    public Animal createAnimal() {
        var name = lionNames[(int) System.currentTimeMillis() % lionNames.length];
        return new Lion("lion " + System.currentTimeMillis(),
                name + " " + System.currentTimeMillis(),
                new BigDecimal(System.currentTimeMillis() % 1000007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("26-12-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}
