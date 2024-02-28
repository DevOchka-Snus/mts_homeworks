package ru.mts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.mts.config.AnimalConfiguration;
import ru.mts.config.AnimalTestConfiguration;
import ru.mts.domain.AnimalType;
import ru.mts.domain.pet.Cat;
import ru.mts.domain.pet.Dog;
import ru.mts.factory.AnimalNameProvider;
import ru.mts.factory.CatFactory;
import ru.mts.service.CreateAnimalService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {AnimalConfiguration.class, AnimalTestConfiguration.class})
@TestPropertySource(locations = {"classpath:application-test.yml"})
public class SpringBootStarterAnimalsTest {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Autowired
    private AnimalNameProvider animalNameProvider;

    @Autowired
    private CatFactory catFactory;

    @Test
    public void createNotNullName() {
        var name = animalNameProvider.generateName(AnimalType.WOLF);

        assertNotNull(name);
    }

    @Test
    public void createArrayOfAnimals() {
        var animals = createAnimalService.createAnimals();

        assertNotNull(animals);
        assertEquals(animals.size(), 10);
    }

    @Test
    public void checkCatFactory() {
        var animal = catFactory.createAnimal();

        // (animal instanceof Cat) - пройдет проверку в случае если там будет класс расширяющий его (TomCat extends Cat)
        assertEquals(Cat.class, animal.getClass());
    }

    @Test
    public void checkErrorCast() {
        var animal = catFactory.createAnimal();

        assertThrows(ClassCastException.class, () -> {
            Dog dog = (Dog) animal;
        });
    }

}
