package ru.mts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.mts.config.AnimalTestConfiguration;
import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.factory.AnimalNameProvider;
import ru.mts.service.CreateAnimalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest(classes = AnimalTestConfiguration.class)
public class SpringBootStarterAnimalsTest {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Autowired
    private AnimalNameProvider animalNameProvider;

    @Test
    public void createNotNullName() {
        var name = animalNameProvider.generateName(AnimalType.WOLF);

        assertNotNull(name);
    }

    @Test
    public void createArrayOfAnimals() {
        Animal[] animals = createAnimalService.createAnimals();

        assertNotNull(animals);

        assertEquals(animals.length, 10);
    }

}
