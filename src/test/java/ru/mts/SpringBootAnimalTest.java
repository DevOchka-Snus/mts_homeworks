package ru.mts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.mts.config.AnimalConfiguration;
import ru.mts.config.AnimalTestConfiguration;
import ru.mts.service.AnimalRepository;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestPropertySource(locations = {"classpath:application-test.yml"})
@SpringBootTest(classes = {AnimalConfiguration.class, AnimalTestConfiguration.class})
public class SpringBootAnimalTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void checkNotNullBean() {
        assertNotNull(animalRepository);
    }

    @Test
    public void checkOnlyLeapYearInArray() {
        var animals = animalRepository.findLeapYearNames();
        var count = Arrays.stream(animals)
                .filter(it -> it.getBirthDate().isLeapYear())
                .count();

        assertEquals(animalRepository.findLeapYearNames().length, count);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 114, 10000000})
    public void checkAllOlderAnimals(int n) {
        var olderAnimals = animalRepository.findOlderAnimal(n);
        var count = Arrays.stream(olderAnimals)
                .filter(it -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now()))
                .count();

        assertEquals(olderAnimals.length, count);
    }

    @Test
    public void checkWrongArgumentTest() {
        assertThrows(IllegalStateException.class, () -> animalRepository.findOlderAnimal(-1));
    }
}
