package ru.mts.service.impl;

import ru.mts.domain.Animal;
import ru.mts.service.SearchService;

import java.time.LocalDate;
import java.util.*;
public class SearchServiceImpl implements SearchService {
    @Override
    public Animal[] findLeapYearNames(Animal[] animals) {
        return Arrays.stream(animals).filter((it) -> it.getBirthDate().isLeapYear()).toArray(Animal[]::new);
    }

    @Override
    public Animal[] findOlderAnimal(Animal[] animals, int n) {
        return Arrays.stream(animals).filter((it) -> it.getBirthDate().plusYears(n).isBefore(LocalDate.now())).toArray(Animal[]::new);
    }

    @Override
    public Animal[] findDuplicate(Animal[] animals) {
        Set<Animal> set = new HashSet<>();
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (set.contains(animal)) {
                result.add(animal);
            } else {
                set.add(animal);
            }
        }
        return result.toArray(Animal[]::new);
    }
}
