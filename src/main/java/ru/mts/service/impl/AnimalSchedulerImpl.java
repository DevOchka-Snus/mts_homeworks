package ru.mts.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mts.domain.Animal;
import ru.mts.service.AnimalRepository;
import ru.mts.service.AnimalScheduler;

@Service
public class AnimalSchedulerImpl implements AnimalScheduler {
    private final AnimalRepository animalRepository;

    public AnimalSchedulerImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void printAnimals() {
        System.out.println("findLeapYearNames");
        var one = animalRepository.findLeapYearNames();
        for (Animal animal : one) {
            System.out.println(animal);
        }

        var two = animalRepository.findOlderAnimal(10000000);
        System.out.println("findOlderAnimal");
        for (Animal animal : two) {
            System.out.println(animal);
        }

        System.out.println("findDuplicate");
        var three = animalRepository.findDuplicate();
        for (Animal animal : three) {
            System.out.println(animal);
        }
    }
}
