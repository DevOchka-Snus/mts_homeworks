package ru.mts.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractAnimal implements Animal {

    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = (Objects.isNull(cost) ? null : cost.setScale(2, RoundingMode.HALF_UP));
        this.character = character;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{ breed='" + getBreed() + '\'' +
                ", name='" + getName() + '\'' +
                ", cost=" + (Objects.isNull(getCost()) ? "null" : getCost().toPlainString()) +
                ", character='" + getCharacter() + '\'' +
                ", birthDate= " + (Objects.isNull(getBirthDate()) ? "null" : String.format("%1$td-%1$tm-%1$tY", getBirthDate()) + " }");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractAnimal that = (AbstractAnimal) o;

        return Objects.equals(breed, that.breed)
                && Objects.equals(name, that.name)
                && Objects.equals(character, that.character)
                && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        int result = breed == null ? 0 : breed.hashCode();
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (character == null ? 0 : character.hashCode());
        result = 31 * result + (birthDate == null ? 0 : birthDate.hashCode());

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
