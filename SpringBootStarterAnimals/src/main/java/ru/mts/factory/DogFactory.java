package ru.mts.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.domain.pet.Dog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.mts.domain.AnimalType.DOG;

@Component(DogFactory.NAME)
public class DogFactory implements AnimalFactory {

    public static final String NAME = "springBootStarterAnimals_DogFactory";

    private final AnimalNameProvider animalNameProvider;

    @Autowired
    public DogFactory(AnimalNameProvider animalNameProvider) {
        this.animalNameProvider = animalNameProvider;
    }

    @Override
    public Animal createAnimal() {
        return new Dog("dog " + System.currentTimeMillis(),
                animalNameProvider.generateName(DOG),
                BigDecimal.valueOf(System.currentTimeMillis() % 1_000_007).setScale(2, RoundingMode.HALF_UP),
                "character " + System.currentTimeMillis(),
                LocalDate.parse("22-02-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        );

    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return DOG.equals(animalType);
    }

}
