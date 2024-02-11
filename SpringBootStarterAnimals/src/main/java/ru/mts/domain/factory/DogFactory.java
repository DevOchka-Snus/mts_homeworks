package ru.mts.domain.factory;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.domain.Animal;
import ru.mts.domain.pet.Dog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DogFactory extends AnimalFactory {
    @Value("${animal.dog.names}")
    private String[] dogNames;

    @Override
    public Animal createAnimal() {
        var name = dogNames[(int) System.currentTimeMillis() % dogNames.length];
        return new Dog("dog " + System.currentTimeMillis(),
                name + " " + System.currentTimeMillis(),
                new BigDecimal(System.currentTimeMillis() % 1000007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("22-02-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}
