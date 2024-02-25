package ru.mts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import ru.mts.config.AnimalTestConfiguration;
import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.domain.pet.Cat;
import ru.mts.domain.pet.Dog;
import ru.mts.factory.AnimalNameProvider;
import ru.mts.factory.CatFactory;
import ru.mts.service.CreateAnimalService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest(classes = AnimalTestConfiguration.class)
public class SpringBootStarterAnimalsTest {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Autowired
    private ApplicationContext applicationContext;

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
    public void checkPrototypeScopeOfCreateAnimalService() {
        CreateAnimalService other = applicationContext.getBean(CreateAnimalService.NAME + "Test", CreateAnimalService.class);

        assertNotEquals(createAnimalService, other);
    }

    @Test
    public void createArrayOfAnimals() {
        Animal[] animals = createAnimalService.createAnimals();

        assertNotNull(animals);

        assertEquals(animals.length, 10);
    }

    @Test
    public void checkCatFactory() {
        var animal = catFactory.createAnimal();
        assertTrue(animal instanceof Cat);
    }

    @Test
    public void checkErrorCast() {
        var animal = catFactory.createAnimal();
        assertThrows(ClassCastException.class, () -> {
            Dog dog = (Dog) animal;
        });
    }
}
