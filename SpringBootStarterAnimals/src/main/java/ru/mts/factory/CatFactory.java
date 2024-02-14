package ru.mts.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.domain.pet.Cat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.mts.domain.AnimalType.CAT;

@Component(CatFactory.NAME)
public class CatFactory implements AnimalFactory {

    public static final String NAME = "springBootStarterAnimals_CatFactory";

    private final AnimalNameProvider animalNameProvider;

    @Autowired
    public CatFactory(AnimalNameProvider animalNameProvider) {
        this.animalNameProvider = animalNameProvider;
    }

    @Override
    public Animal createAnimal() {
        return new Cat("cat ",
                animalNameProvider.generateName(CAT),
                BigDecimal.valueOf(System.currentTimeMillis() % 1_000_007).setScale(2, RoundingMode.HALF_UP),
                "character ",
                LocalDate.parse("20-02-1488", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    @Override
    public boolean isApplicable(AnimalType animalType) {
        return CAT.equals(animalType);
    }

}
