package ru.mts.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.mts.domain.Animal;
import ru.mts.domain.AnimalType;
import ru.mts.domain.factory.*;
import ru.mts.service.CreateAnimalService;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(scopeName = "prototype")
public class CreateAnimalServiceImpl implements CreateAnimalService {
    private AnimalType animalType;

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public Animal[] createAnimals() {
        List<Animal> animals = new ArrayList<>();
        AnimalFactory animalFactory = switch (animalType) {
            case CAT -> new CatFactory();
            case DOG -> new DogFactory();
            case WOLF -> new WolfFactory();
            default -> new LionFactory();
        };
        for (int i = 0; i < 10; i++) {
            animals.add(animalFactory.createAnimal());
        }
        return animals.toArray(Animal[]::new);
    }
}