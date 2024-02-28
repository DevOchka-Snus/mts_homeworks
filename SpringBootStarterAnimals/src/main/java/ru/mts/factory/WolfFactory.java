package ru.mts.factory;

import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.domain.predator.Wolf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.mts.domain.AnimalType.WOLF;

public class WolfFactory implements AnimalFactory {

    public static final String NAME = "springBootStarterAnimals_WolfFactory";

    private final AnimalNameProvider animalNameProvider;

    public WolfFactory(AnimalNameProvider animalNameProvider) {
        this.animalNameProvider = animalNameProvider;
    }

    @Override
    public Animal createAnimal() {
        return new Wolf("wolf " + System.currentTimeMillis(),
                animalNameProvider.generateName(WOLF),
                BigDecimal.valueOf(System.currentTimeMillis() % 1_000_007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("22-02-1003", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return WOLF.equals(animalType);
    }

}
