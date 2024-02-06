import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.domain.Animal;
import ru.mts.domain.pet.Cat;
import ru.mts.domain.pet.Dog;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.AnimalRepository;
import ru.mts.service.impl.CreateAnimalServiceImpl;
import ru.mts.service.impl.AnimalRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalSearchTest {
    @Nested
    class EqualsTest {
        @Test
        public void equalNull() {
            Animal animal1 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            assertNotEquals(animal1, null);
        }
        @Test
        public void equalDifferentAnimals() {
            Animal animal1 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            Animal animal2 = new Dog("q", "s", new BigDecimal(13), "qer", LocalDate.now());
            assertNotEquals(animal1, animal2);
        }

        @Test
        public void equalDifferentAnimalsWithEqualType() {
            Animal animal1 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            Animal animal2 = new Cat("c", "b", new BigDecimal(12), "qwr", LocalDate.now());
            assertNotEquals(animal1, animal2);
        }

        @Test
        public void equalAnimalWithHimself() {
            Animal animal = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            assertEquals(animal, animal);
        }

        @Test
        public void equalSameAnimals() {
            Animal animal1 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            Animal animal2 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            assertEquals(animal1, animal2);
        }

        @Test
        public void equalDifferentAnimalsWithEqualParams() {
            Animal animal1 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            Animal animal2 = new Dog("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            assertNotEquals(animal1, animal2);
        }

        @Test
        public void equalAnimalsWithDifferentOrderOfParams() {
            Animal animal1 = new Cat("a", "b", new BigDecimal(12), "qwr", LocalDate.now());
            Animal animal2 = new Cat("b", "qwr", new BigDecimal(12), "a", LocalDate.now());
            assertNotEquals(animal1, animal2);
        }
    }

  /*  @Nested
    class FindLeapYearNamesTest {
        static Animal[] animals;
        static Animal[] leapYearAnimals;

        @BeforeAll
        public static void initAnimals(){
            CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
            animals = createAnimalService.createAnimals();
            AnimalRepository animalRepository = new AnimalRepositoryImpl();
            leapYearAnimals = animalRepository.findLeapYearNames(animals);
        }

        @Test
        public void checkOnlyLeapYearInArray() {
            assertEquals(leapYearAnimals.length, Arrays.stream(leapYearAnimals).filter(it -> it.getBirthDate().isLeapYear()).count());
        }

        @Test
        public void checkAllLeapYearInArray() {
            assertArrayEquals(leapYearAnimals, Arrays.stream(animals).filter(it -> it.getBirthDate().isLeapYear()).toArray(Animal[]::new));
        }
    }

    @Nested
    public class FindOlderAnimalTest {
        static Animal[] animals;
        static AnimalRepository animalRepository;

        @BeforeAll
        public static void initAnimals() {
            CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
            animals = createAnimalService.createAnimals();
            animalRepository = new AnimalRepositoryImpl(createAnimalService);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 114, 10000000})
        public void checkAllOlderAnimals(int n) {
            if (n <= 0) {
                assertThrows(IllegalStateException.class, () -> animalRepository.findOlderAnimal(animals, n));
                return;
            }
            var olderAnimals = animalRepository.findOlderAnimal(animals, n);
            assertEquals(olderAnimals.length, Arrays.stream(animals).filter(it -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now())).count());
            var target = Arrays.stream(animals).filter((it) -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now())).toArray(Animal[]::new);
            assertArrayEquals(target, olderAnimals);
        }
    }

    @Nested
    class FindDuplicatesTest {
        private static Animal[] animals;
        private static Animal[] duplicateAnimals;

        @BeforeAll
        public static void initAnimals() {
            CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
            animals = createAnimalService.createAnimals();
            AnimalRepository animalRepository = new AnimalRepositoryImpl();
            duplicateAnimals = animalRepository.findDuplicate(animals);
        }

        @Test
        public void checkAmountOfDuplicates() {
            Set<Animal> set = new HashSet<>();
            int result = 0;
            for (Animal animal : animals) {
                if (set.contains(animal)) {
                    result++;
                } else {
                    set.add(animal);
                }
            }
            assertEquals(duplicateAnimals.length, result);
        }

        @Test
        public void checkAllDuplicates() {
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
            assertArrayEquals(target, duplicateAnimals);
        }
    }*/
}
