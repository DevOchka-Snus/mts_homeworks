package ru.mts.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractAnimal implements Animal {
    protected String animalType;
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    public AbstractAnimal(String animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.animalType = animalType;
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return  "breed='" + getBreed() + '\'' +
                ", name='" + getName() + '\'' +
                ", cost=" + getCost().toString() +
                ", character='" + getCharacter() + '\'' +
                ", birthDate= " + getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractAnimal animal = (AbstractAnimal) o;

        if (!breed.equals(animal.breed)) return false;
        if (!name.equals(animal.name)) return false;
        if (!cost.equals(animal.cost)) return false;
        if (!character.equals(animal.character)) return false;
        return birthDate.equals(animal.birthDate);
    }

    @Override
    public int hashCode() {
        int result = breed != null ? breed.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (character != null ? character.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }
}
