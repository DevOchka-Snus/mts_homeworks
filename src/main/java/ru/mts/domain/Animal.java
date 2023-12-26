package ru.mts.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The Animal interface contains methods
 * that return the values of the corresponding fields in the AbstractAnimal class
 *
 * @author vladi
 * @version 1.0 17 December 2023
 */
public interface Animal {
    /**
     * Method return the value of breed field
     *
     * @return the value of the breed field
     */
    String getBreed();

    /**
     * Method return the value of name field
     *
     * @return the value of the name field
     */
    String getName();

    /**
     * Method return the value of breed field
     *
     * @return the value of the breed field
     */
    BigDecimal getCost();

    /**
     * Method return the value of character field
     *
     * @return the value of the character field
     */
    String getCharacter();

    /**
     * Method return the value of birthDate field
     *
     * @return the value of the birthDate field
     */
    LocalDate getBirthDate();
}