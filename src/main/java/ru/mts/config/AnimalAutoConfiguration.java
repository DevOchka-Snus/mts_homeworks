package ru.mts.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(AppConfigProperties.class)
@Configuration
@ConditionalOnClass(AnimalConfiguration.class)
public class AnimalAutoConfiguration {

}
