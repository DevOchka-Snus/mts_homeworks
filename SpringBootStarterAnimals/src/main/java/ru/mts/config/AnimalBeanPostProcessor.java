package ru.mts.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.mts.domain.AnimalType;
import ru.mts.service.impl.CreateAnimalServiceImpl;

public class AnimalBeanPostProcessor implements BeanPostProcessor {

    public static final String NAME = "mts_AnimalBeanPostProcessor";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalServiceImpl) {
            CreateAnimalServiceImpl animalService = (CreateAnimalServiceImpl) bean;
            long a = System.currentTimeMillis() % 1_000_007, b = a / 2;
            long x = ((long) (Math.random() * 1_000_000)) % 1_000_007;
            if (x % 2 == 0) {
                animalService.setAnimalType(x > b ? AnimalType.DOG : AnimalType.CAT);
            } else {
                animalService.setAnimalType(x > b ? AnimalType.LION : AnimalType.WOLF);
            }
        }

        return bean;
    }

}
