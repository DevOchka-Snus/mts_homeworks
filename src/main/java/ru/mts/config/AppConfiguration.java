package ru.mts.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.impl.AnimalRepositoryImpl;
import ru.mts.service.impl.CreateAnimalServiceImpl;

@Configuration
public class AppConfiguration {

    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Bean(name = CreateAnimalService.NAME)
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalBeanPostProcessor animalBeanPostProcessor() {
        return new AnimalBeanPostProcessor();
    }

    @Bean(name = AnimalRepository.NAME, initMethod = "postConstruct")
    public AnimalRepository animalRepository(@Autowired ObjectProvider<CreateAnimalService> createAnimalServicesBeanProvider) {
        return new AnimalRepositoryImpl(createAnimalServicesBeanProvider);
    }

}
