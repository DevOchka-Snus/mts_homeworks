package ru.mts.domain;

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

/**
 * AnimalFactory class create Animal objects
 *
 * @version 1.0
 * @author vladi
 * */
public class AnimalFactory {
    /**
     * Public method create Animal object
     *
     * @param flag s value that defines the implementation of Animal
     * @return Animal object
     * */
    public Animal createAnimal(int flag) {
        if (flag % 2 == 0) {
            return createPet(flag, new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        } else {
            return createPredator(flag, new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        }
    }

    /**
     * Private method create Pet object
     *
     * @param flag is value that defines the implementation of Pet
     * @param cost is value for the constructor of Pet object
     * @return Pet object
     */
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
     *
     * @param flag is value that defines the implementation of Predator
     * @param cost is value for the constructor of Predator object
     * @return Predator object
     */
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
