package ru.mts.domain.factory;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.domain.Animal;
import ru.mts.domain.pet.Cat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CatFactory extends AnimalFactory {
    @Value("${animal.cat.names}")
    private String[] catNames;

    @Override
    public Animal createAnimal() {
        var name = catNames[(int) System.currentTimeMillis() % catNames.length];
        return new Cat("cat ",
                name + " ",
                new BigDecimal(System.currentTimeMillis() % 1000007).setScale(2, RoundingMode.HALF_UP),
                "character ",
                LocalDate.parse("20-02-1488", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}
