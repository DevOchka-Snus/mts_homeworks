package ru.mts.scheduling;

public interface AnimalSchedulerMBean {

    String NAME = "springBootStarterAnimals_AnimalScheduler";

    void printAnimals();

    void printDuplicates();

    void printAverageAge();

}
