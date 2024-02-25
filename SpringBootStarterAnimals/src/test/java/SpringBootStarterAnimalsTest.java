import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mts.config.AnimalBeanPostProcessor;
import ru.mts.config.AnimalConfigurationProperties;
import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.factory.*;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.CreateAnimalServiceImpl;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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

    public void test() {

    }

    @TestConfiguration
    @ConfigurationProperties
    @TestPropertySource(locations = "classpath:application-test.yml")
    static class Config {
        @Value("#{'${animal.dog.names}'.split(',')}")
        private List<String> dogNames;
        @Value("#{'${animal.cat.names}'.split(',')}")
        private List<String> catNames;
        @Value("#{'${animal.lion.names}'.split(',')}")
        private List<String> lionNames;
        @Value("#{'${animal.wolf.names}'.split(',')}")
        private List<String> wolfNames;

        @Bean(name = CatFactory.NAME)
        public CatFactory catFactory(@Autowired AnimalNameProvider animalNameProvider) {
            return new CatFactory(animalNameProvider);
        }

        @Bean(name = DogFactory.NAME)
        public DogFactory dogFactory(@Autowired AnimalNameProvider animalNameProvider) {
            return new DogFactory(animalNameProvider);
        }

        @Bean(name = LionFactory.NAME)
        public LionFactory lionFactory(@Autowired AnimalNameProvider animalNameProvider) {
            return new LionFactory(animalNameProvider);
        }

        @Bean(name = WolfFactory.NAME)
        public WolfFactory wolfFactory(@Autowired AnimalNameProvider animalNameProvider) {
            return new WolfFactory(animalNameProvider);
        }

        @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        @Bean(name = CreateAnimalService.NAME)
        public CreateAnimalService createAnimalService(@Autowired Map<String, AnimalFactory> animalFactories) {
            return new CreateAnimalServiceImpl(animalFactories);
        }

        @Bean
        public AnimalBeanPostProcessor animalBeanPostProcessor() {
            return new AnimalBeanPostProcessor();
        }

        @Bean(name = AnimalNameProvider.NAME)
        public AnimalNameProvider animalNameProvider() {
            var properties = new AnimalConfigurationProperties();
            properties.setCatNames(catNames);
            properties.setDogNames(dogNames);
            properties.setLionNames(lionNames);
            properties.setWolfNames(wolfNames);
            return new AnimalRandomNameProvider(properties);
        }
    }


}
