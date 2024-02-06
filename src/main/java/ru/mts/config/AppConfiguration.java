package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.CreateAnimalServiceImpl;

@Configuration
public class AppConfiguration {
    @Bean
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
    @Bean
    public AnimalBeanPostProcessor animalBeanPostProcessor() {
        return new AnimalBeanPostProcessor();
    }
}
