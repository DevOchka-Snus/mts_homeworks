package ru.mts.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(AnimalConfiguration.class)
public class AnimalAutoConfiguration {

}
