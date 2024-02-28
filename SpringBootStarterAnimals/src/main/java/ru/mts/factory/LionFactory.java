package ru.mts.factory;

import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.domain.predator.Lion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.mts.domain.AnimalType.LION;

public class LionFactory implements AnimalFactory {

    public static final String NAME = "springBootStarterAnimals_LionFactory";

    private final AnimalNameProvider animalNameProvider;

    public LionFactory(AnimalNameProvider animalNameProvider) {
        this.animalNameProvider = animalNameProvider;
    }

    @Override
    public Animal createAnimal() {
        return new Lion("lion " + System.currentTimeMillis(),
                animalNameProvider.generateName(LION),
                BigDecimal.valueOf(System.currentTimeMillis() % 1_000_007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("26-12-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return LION.equals(animalType);
    }

}
