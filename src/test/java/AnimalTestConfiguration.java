import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestPropertySource;
import ru.mts.config.AnimalBeanPostProcessor;
import ru.mts.config.AnimalConfigurationProperties;
import ru.mts.factory.*;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.CreateAnimalServiceImpl;

import java.util.Map;

@TestConfiguration
@SpringBootConfiguration
@TestPropertySource(locations = "classpath:application-test.yml")
@EnableConfigurationProperties(AnimalConfigurationProperties.class)
public class AnimalTestConfiguration {

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
    public AnimalNameProvider animalNameProvider(@Autowired AnimalConfigurationProperties animalConfigurationProperties) {
        return new AnimalRandomNameProvider(animalConfigurationProperties);
    }
}
