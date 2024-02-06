package ru.mts;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mts.domain.Animal;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.AnimalRepository;
import ru.mts.service.impl.CreateAnimalServiceImpl;
import ru.mts.service.impl.AnimalRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("ru")
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        AnimalRepository animalRepository = (AnimalRepository) context.getBean("animalRepositoryImpl");
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
        context.close();
    }
}