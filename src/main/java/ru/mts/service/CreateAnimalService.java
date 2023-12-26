package ru.mts.service;

import ru.mts.domain.Animal;
import ru.mts.domain.pet.Cat;
import ru.mts.domain.pet.Dog;
import ru.mts.domain.pet.Pet;
import ru.mts.domain.predator.Lion;
import ru.mts.domain.predator.Predator;
import ru.mts.domain.predator.Wolf;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The CreateAnimalService interface contains methods
 * that create Animal objects
 * @version 1.0
 * @author vladi
 * */
public interface CreateAnimalService {
    /**
     * Default method create array of Animal objects
     * @return array of Animal objects
     * */
    default Animal[] createAnimals() {
        List<Animal> animals = new ArrayList<>();
        var cost = new BigDecimal("100").setScale(2, RoundingMode.HALF_UP);
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                animals.add(createPredator(i, cost));
            } else {
                animals.add(createPet(i, cost));
            }
        }
        return animals.toArray(Animal[]::new);
    }

    /**
     * Private method create Pet object
     * @param flag is value that defines the implementation of Pet
     * @param cost is value for the constructor of Pet object
     * @return Pet object
     * */
    private Pet createPet(int flag, BigDecimal cost) {
        if (flag < 5) {
            return new Dog("dog " + System.currentTimeMillis(),
                    "Mops " + System.currentTimeMillis(),
                    cost.add(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP)),
                    "character " + System.currentTimeMillis(),
                    LocalDate.parse("22-02-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else {
            return new Cat("cat ",
                    "Leopold ",
                    cost,
                    "character ",
                    LocalDate.parse("20-02-1488", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
    }

    /**
     * Private method create Pet object
     * @param flag is value that defines the implementation of Predator
     * @param cost is value for the constructor of Predator object
     * @return Predator object
     * */
    private Predator createPredator(int flag, BigDecimal cost) {
        if (flag < 5) {
            return new Lion("lion " + System.currentTimeMillis(),
                    "Vanomas " + System.currentTimeMillis(),
                    cost.add(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP),
                    "character " + System.currentTimeMillis(),
                    LocalDate.parse("26-12-2023", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else {
            return new Wolf("wolf " + System.currentTimeMillis(),
                    "Kuplinov " + System.currentTimeMillis(),
                    cost.add(new BigDecimal(200).setScale(2, RoundingMode.HALF_UP)),
                    "character " + System.currentTimeMillis(),
                    LocalDate.parse("22-02-1003", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
    }
}