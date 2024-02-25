package ru.mts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.mts.domain.AnimalType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ru.mts.domain.AnimalType.*;

@ConfigurationProperties
public class AnimalConfigurationProperties {

    @Value("#{'${animal.dog.names}'.split(',')}")
    private List<String> dogNames;
    @Value("#{'${animal.cat.names}'.split(',')}")
    private List<String> catNames;
    @Value("#{'${animal.lion.names}'.split(',')}")
    private List<String> lionNames;
    @Value("#{'${animal.wolf.names}'.split(',')}")
    private List<String> wolfNames;

    public void setDogNames(List<String> dogNames) {
        this.dogNames = dogNames;
    }

    public void setCatNames(List<String> catNames) {
        this.catNames = catNames;
    }

    public void setLionNames(List<String> lionNames) {
        this.lionNames = lionNames;
    }

    public void setWolfNames(List<String> wolfNames) {
        this.wolfNames = wolfNames;
    }

    public List<String> getDogNames() {
        return Collections.unmodifiableList(dogNames);
    }

    public List<String> getCatNames() {
        return Collections.unmodifiableList(catNames);
    }

    public List<String> getLionNames() {
        return Collections.unmodifiableList(lionNames);
    }

    public List<String> getWolfNames() {
        return Collections.unmodifiableList(wolfNames);
    }

    public Map<AnimalType, List<String>> collectAnimalsNames() {
        return Map.of(
                DOG, getDogNames(),
                CAT, getCatNames(),
                LION, getLionNames(),
                WOLF, getWolfNames()
        );
    }

}
