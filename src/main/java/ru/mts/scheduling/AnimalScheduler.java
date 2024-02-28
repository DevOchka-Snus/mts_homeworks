package ru.mts.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.config.AppConfigProperties;
import ru.mts.service.AnimalRepository;

import java.util.Objects;

@Component(AnimalSchedulerMBean.NAME)
public class AnimalScheduler implements AnimalSchedulerMBean {

    private static final Logger log = LoggerFactory.getLogger(AnimalSchedulerMBean.class);

    private final AnimalRepository animalRepository;

    private final boolean logDebugData;
    private final int animalCount;

    @Autowired
    public AnimalScheduler(AnimalRepository animalRepository, AppConfigProperties appConfigProperties) {
        if (Objects.isNull(appConfigProperties)) {
            throw new RuntimeException("Karamba!");
        }

        this.animalRepository = animalRepository;
        this.logDebugData = appConfigProperties.getLogDebugData();
        this.animalCount = appConfigProperties.getAnimalCount();
    }

    @Scheduled(fixedRate = 60_000)
    @Override
    public void printAnimals() {
        printInfo("findLeapYearNames");
        var one = animalRepository.findLeapYearNames();
        for (var animal : one.entrySet()) {
            printInfo(animal.getKey() + ": " + animal.getValue().toString());
        }

        var two = animalRepository.findOlderAnimal(animalCount);
        printInfo("findOlderAnimal");
        for (var animal : two.entrySet()) {
            printInfo(animal.getKey().toString() + ": " + animal.getValue());
        }

        printInfo("findDuplicate");
        var three = animalRepository.findDuplicate();
        for (var animal : three.entrySet()) {
            printInfo(animal.getKey() + ": " + animal.getValue());
        }

    }

    private void printInfo(String message) {
        if (logDebugData) {
            log.info(message);
        } else {
            System.out.println(message);
        }

    }

}
