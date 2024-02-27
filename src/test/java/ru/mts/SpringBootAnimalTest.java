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
import ru.mts.domain.Animal;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestPropertySource(locations = {"classpath:application-test.yml"})
@SpringBootTest(classes = {AnimalConfiguration.class, AnimalTestConfiguration.class})
public class SpringBootAnimalTest {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void checkOnlyLeapYearInArray() {
        var animals = createAnimalService.createAnimals();
        var count = Arrays.stream(animals)
                .filter(it -> it.getBirthDate().isLeapYear())
                .count();

        assertEquals(animalRepository.findLeapYearNames().length, count);
    }

    @Test
    public void checkAllLeapYearInArray() {
        var animals = createAnimalService.createAnimals();
        var array = Arrays.stream(animals)
                .filter(it -> it.getBirthDate().isLeapYear())
                .toArray(Animal[]::new);

        assertArrayEquals(animalRepository.findLeapYearNames(), array);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 114, 10000000})
    public void checkAllOlderAnimals(int n) {
        var animals = createAnimalService.createAnimals();
        var olderAnimals = animalRepository.findOlderAnimal(n);
        var count = Arrays.stream(animals)
                .filter(it -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now()))
                .count();

        assertEquals(olderAnimals.length, count);

        var target = Arrays.stream(animals)
                .filter(it -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now()))
                .toArray(Animal[]::new);

        assertArrayEquals(target, olderAnimals);
    }

    @Test
    public void checkAmountOfDuplicates() {
        var animals = createAnimalService.createAnimals();
        Set<Animal> set = new HashSet<>();
        int result = 0;
        for (Animal animal : animals) {
            if (set.contains(animal)) {
                result++;
            } else {
                set.add(animal);
            }
        }

        assertEquals(animalRepository.findDuplicate().length, result);
    }

    @Test
    public void checkAllDuplicates() {
        var animals = createAnimalService.createAnimals();
        Set<Animal> set = new HashSet<>();
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (set.contains(animal)) {
                result.add(animal);
            } else {
                set.add(animal);
            }
        }

        var target = result.toArray(Animal[]::new);
        assertArrayEquals(target, animalRepository.findDuplicate());
    }

}
