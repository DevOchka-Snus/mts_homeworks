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
import ru.mts.service.CreateAnimalService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
    public void checkNotNullBean() {
        assertNotNull(animalRepository);
        assertNotNull(createAnimalService);
    }

    @Test
    public void checkCreateAnimals() {
        var animals = createAnimalService.createAnimals();

        for (var animalMap : animals.entrySet()) {
            assertNotNull(animalMap.getKey());
            assertNotNull(animalMap.getValue());
            for (var animal : animalMap.getValue()) {
                assertNotNull(animal);
            }
        }
    }

    @Test
    public void checkOnlyLeapYearInArray() {
        var animals = animalRepository.findLeapYearNames();
        boolean wrong = false;
        for (var animal : animals.entrySet()) {
            if (!animal.getValue().isLeapYear()) {
                wrong = true;
                break;
            }
        }
        assertFalse(wrong);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 114, 10000000})
    public void checkAllOlderAnimals(int n) {
        var olderAnimals = animalRepository.findOlderAnimal(n);

        boolean wrong = false;

        var now = LocalDate.now();

        for (var animal : olderAnimals.entrySet()) {
            assertNotNull(animal.getKey());
            int age = animal.getValue();
            wrong = animal.getKey().getBirthDate().plusYears(age).isAfter(now);
        }

        assertFalse(wrong);
    }

    @Test
    public void checkFindDuplicates() {
        var duplicates = animalRepository.findDuplicate();

        for (var animal : duplicates.entrySet()) {
            assertNotNull(animal.getKey());
        }
    }

    @Test
    public void checkWrongArgumentTest() {
        assertThrows(IllegalStateException.class, () -> animalRepository.findOlderAnimal(-1));
    }
}
