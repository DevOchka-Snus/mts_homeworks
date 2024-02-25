package ru.mts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.config.AnimalTestConfiguration;
import ru.mts.domain.Animal;
import ru.mts.factory.AnimalFactory;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.AnimalRepositoryImpl;
import ru.mts.service.impl.CreateAnimalServiceImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest(classes = AnimalTestConfiguration.class)
public class SpringBootAnimalTest {
    @Autowired
    Map<String, AnimalFactory> animalFactories;

    @Autowired
    ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider;

    @Spy
    private CreateAnimalService createAnimalService;

    @Spy
    private AnimalRepository animalRepository;

    {
        createAnimalService = new CreateAnimalServiceImpl(animalFactories);
        animalRepository = new AnimalRepositoryImpl(createAnimalServicesBeanProvider);
    }

    @Test
    public void checkOnlyLeapYearInArray() {
        var animals = createAnimalService.createAnimals();
        assertEquals(animalRepository.findLeapYearNames().length, Arrays.stream(animals).filter(it -> it.getBirthDate().isLeapYear()).count());
    }

    @Test
    public void checkAllLeapYearInArray() {
        var animals = createAnimalService.createAnimals();
        assertArrayEquals(animalRepository.findLeapYearNames(), Arrays.stream(animals).filter(it -> it.getBirthDate().isLeapYear()).toArray(Animal[]::new));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 114, 10000000})
    public void checkAllOlderAnimals(int n) {
        var animals = createAnimalService.createAnimals();
        var olderAnimals = animalRepository.findOlderAnimal(n);
        assertEquals(olderAnimals.length, Arrays.stream(animals).filter(it -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now())).count());
        var target = Arrays.stream(animals).filter((it) -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now())).toArray(Animal[]::new);
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
