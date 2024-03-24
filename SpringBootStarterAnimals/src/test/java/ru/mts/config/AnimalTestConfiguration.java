package ru.mts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import ru.mts.factory.*;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.CreateAnimalServiceImpl;

import java.util.Map;

@TestConfiguration
@EnableConfigurationProperties(AnimalConfigurationProperties.class)
public class AnimalTestConfiguration {

    private static final String TEST_BEAN_NAME_SUFFIX = "Test";

    @Primary
    @Bean(name = CatFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public CatFactory catFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new CatFactory(animalNameProvider);
    }

    @Primary
    @Bean(name = DogFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public DogFactory dogFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new DogFactory(animalNameProvider);
    }

    @Primary
    @Bean(name = LionFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public LionFactory lionFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new LionFactory(animalNameProvider);
    }

    @Primary
    @Bean(name = WolfFactory.NAME + TEST_BEAN_NAME_SUFFIX)
    public WolfFactory wolfFactory(@Autowired AnimalNameProvider animalNameProvider) {
        return new WolfFactory(animalNameProvider);
    }

    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME + TEST_BEAN_NAME_SUFFIX)
    public CreateAnimalService createAnimalService(@Autowired Map<String, AnimalFactory> animalFactories) {
        return new CreateAnimalServiceImpl(animalFactories);
    }

    @Primary
    @Bean(name = AnimalBeanPostProcessor.NAME + TEST_BEAN_NAME_SUFFIX)
    public AnimalBeanPostProcessor animalBeanPostProcessor() {
        return new AnimalBeanPostProcessor();
    }

    @Primary
    @Bean(name = AnimalNameProvider.NAME + TEST_BEAN_NAME_SUFFIX)
    public AnimalNameProvider animalNameProvider(@Autowired AnimalConfigurationProperties animalConfigurationProperties) {
        return new AnimalRandomNameProvider(animalConfigurationProperties);
    }

}
