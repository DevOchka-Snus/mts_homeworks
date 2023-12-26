package ru.mts;

import ru.mts.domain.Animal;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.SearchService;
import ru.mts.service.impl.CreateAnimalServiceImpl;
import ru.mts.service.impl.SearchServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();
        System.out.println("findLeapYearNames");
        var one = searchService.findLeapYearNames(createAnimalService.createAnimals());
        for (Animal animal : one) {
            System.out.println(animal);
        }
        var two = searchService.findOlderAnimal(createAnimalService.createAnimals(), 100);
        System.out.println("findOlderAnimal");
        for (Animal animal : two) {
            System.out.println(animal);
        }
        System.out.println("findDuplicate");
        var three = searchService.findDuplicate(createAnimalService.createAnimals());
        for (Animal animal : three) {
            System.out.println(animal);
        }
    }
}