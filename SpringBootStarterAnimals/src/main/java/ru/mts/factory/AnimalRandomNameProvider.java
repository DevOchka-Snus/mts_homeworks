package ru.mts.factory;

import ru.mts.config.AnimalConfigurationProperties;
import ru.mts.domain.AnimalType;

import java.util.Collections;
import java.util.Random;

/**
 * @author Vladislav Gruzdov
 */
public class AnimalRandomNameProvider implements AnimalNameProvider {

    private final AnimalConfigurationProperties animalConfigurationProperties;
    private final Random random;

    {
        random = new Random();
    }

    public AnimalRandomNameProvider(AnimalConfigurationProperties animalConfigurationProperties) {
        this.animalConfigurationProperties = animalConfigurationProperties;
    }

    @Override
    public String generateName(AnimalType animalType) {
        var animalsNames = animalConfigurationProperties.collectAnimalsNames();
        var names = animalsNames.getOrDefault(animalType, Collections.emptyList());
        if (names.isEmpty()) {
            throw new RuntimeException("same important message");
        }

        return String.format(
                "%s_%d",
                names.get(random.nextInt(names.size())),
                System.currentTimeMillis()
        );

    }

}
