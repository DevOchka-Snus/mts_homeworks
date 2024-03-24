package ru.mts.scheduling;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.config.AppConfigProperties;
import ru.mts.exception.ArgumentIsNotGreaterThanZeroException;
import ru.mts.exception.EmptyListException;
import ru.mts.exception.IllegalAnimalTypeException;
import ru.mts.exception.NullCollectionException;
import ru.mts.service.AnimalRepository;
import ru.mts.service.CreateAnimalService;

import java.util.Objects;

@Component(AnimalSchedulerMBean.NAME)
public class AnimalScheduler implements AnimalSchedulerMBean {

    private static final Logger log = LoggerFactory.getLogger(AnimalSchedulerMBean.class);

    private final CreateAnimalService createAnimalService;

    private final AnimalRepository animalRepository;

    private final int animalCount;

    @Autowired
    public AnimalScheduler(CreateAnimalService createAnimalService, AnimalRepository animalRepository, AppConfigProperties appConfigProperties) {
        if (Objects.isNull(appConfigProperties)) {
            throw new RuntimeException("Karamba!");
        }
        this.createAnimalService = createAnimalService;
        this.animalRepository = animalRepository;
        this.animalCount = appConfigProperties.getAnimalCount();
    }

    @PostConstruct
    private void postConstruct() {
        Thread printDuplicatesThread = new Thread(() -> {
            while (true) {
                printDuplicates();
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        });

        printDuplicatesThread.setName("printDuplicatesThread");

        Thread printAverageAgeThread = new Thread(() -> {
            while (true) {
                printAverageAge();
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        });

        printAverageAgeThread.setName("printAverageAgeThread");

        printDuplicatesThread.start();
        printAverageAgeThread.start();
    }

    @Scheduled(fixedRate = 60_000)
    @Override
    public void printAnimals() {
        try {
            log.info("findLeapYearNames");
            var one = animalRepository.findLeapYearNames();
            for (var animal : one.entrySet()) {
                log.info(animal.getKey() + ": " + animal.getValue().toString());
            }

            var two = animalRepository.findOlderAnimal(animalCount);
            log.info("findOlderAnimal");
            for (var animal : two.entrySet()) {
                log.info(animal.getKey().toString() + ": " + animal.getValue());
            }

            log.info("findOldAndExpensive");
            var five = animalRepository.findOldAndExpensive(createAnimalService.createAnimals().values().stream().findFirst().get());
            for (var animal : five) {
                log.info(animal.toString());
            }

            log.info("findMinConstAnimals");
            var six = animalRepository.findMinConstAnimals(createAnimalService.createAnimals().values().stream().findFirst().get());
            for (var animal : six) {
                log.info(animal.toString());
            }
        } catch (ArgumentIsNotGreaterThanZeroException e) {
            log.error(e.getMessage());
        } catch (EmptyListException e) {
            log.error(e.getMessage());
        } catch (IllegalAnimalTypeException e) {
            log.error(e.getMessage());
        } catch (NullCollectionException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public void printDuplicates() {
        try {
            var three = animalRepository.findDuplicate();
            StringBuilder result = new StringBuilder();
            for (var animal : three.entrySet()) {
                result.append(animal.getKey()).append(": ").append(animal.getValue());
            }
            var name = Thread.currentThread().getName();
            log.info(name + ": " + (result.toString().isEmpty() ? "not duplicates" : result.toString()));
        } catch (ArgumentIsNotGreaterThanZeroException e) {
            log.error(e.getMessage());
        } catch (EmptyListException e) {
            log.error(e.getMessage());
        } catch (IllegalAnimalTypeException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void printAverageAge() {
        try {
            var four = animalRepository.findAverageAge(createAnimalService.createAnimals().values().stream().findFirst().get());
            var name = Thread.currentThread().getName();
            log.info(name + ": " + four);
        } catch (ArgumentIsNotGreaterThanZeroException e) {
            log.error(e.getMessage());
        } catch (EmptyListException e) {
            log.error(e.getMessage());
        } catch (IllegalAnimalTypeException e) {
            log.error(e.getMessage());
        } catch (NullCollectionException e) {
            log.error(e.getMessage());
        }
    }
}
