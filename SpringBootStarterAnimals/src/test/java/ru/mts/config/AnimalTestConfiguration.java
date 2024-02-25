package ru.mts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.TestPropertySource;
import ru.mts.factory.*;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.CreateAnimalServiceImpl;

import java.util.Map;

@TestConfiguration
@SpringBootConfiguration
@TestPropertySource(locations = "classpath:application-test.yml")
@EnableConfigurationProperties(AnimalConfigurationProperties.class)
public class AnimalTestConfiguration {

    private static final String TEST_BEAN_NAME_SUFFIX = "Test";

    @Bean(name = CatFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public CatFactory catFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new CatFactory(animalNameProvider);
    }

    @Bean(name = DogFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public DogFactory dogFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new DogFactory(animalNameProvider);
    }

    @Bean(name = LionFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public LionFactory lionFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new LionFactory(animalNameProvider);
    }

    @Bean(name = WolfFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public WolfFactory wolfFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new WolfFactory(animalNameProvider);
    }

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME + TEST_BEAN_NAME_SUFFIX)
    public CreateAnimalService createAnimalService(@Autowired Map<String, AnimalFactory> animalFactories) {
        return new CreateAnimalServiceImpl(animalFactories);
    }

    @Bean(name = AnimalBeanPostProcessor.NAME + TEST_BEAN_NAME_SUFFIX)
    public AnimalBeanPostProcessor animalBeanPostProcessor() {
        return new AnimalBeanPostProcessor();
    }

    @Bean(name = AnimalNameProvider.NAME + TEST_BEAN_NAME_SUFFIX)
    public AnimalNameProvider animalNameProvider(@Autowired AnimalConfigurationProperties animalConfigurationProperties) {
        return new AnimalRandomNameProvider(animalConfigurationProperties);
    }

}
