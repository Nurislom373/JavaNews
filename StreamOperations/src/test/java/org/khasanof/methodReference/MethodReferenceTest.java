package org.khasanof.methodReference;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: Nurislom
 * <br/>
 * Date: 28.05.2023
 * <br/>
 * Time: 9:12
 * <br/>
 * Package: org.khasanof.methodReference
 */
public class MethodReferenceTest {

    private final List<Dish> dishes = List.of(
            new Dish("Osh", 700),
            new Dish("Jiz", 500),
            new Dish("Lavash", 200)
    );

    @Test
    void groupByLambda() {
        Map<Dish.Level, List<Dish>> collected = dishes.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return Dish.Level.DIET;
                    else if (dish.getCalories() <= 700) return Dish.Level.NORMAL;
                    else return Dish.Level.FAT;
                }));
        System.out.println("collected = " + collected);
    }

    @Test
    void groupByMethodReference() {
        Map<Dish.Level, List<Dish>> collected = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getLevel));
        System.out.println("collected = " + collected);
    }


}


class Dish {

    private String name;
    private Integer calories;

    public Dish(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public Level getLevel() {
        if (this.calories <= 400) return Level.DIET;
        else if (this.calories <= 700) return Level.NORMAL;
        else return Level.FAT;
    }

    enum Level {
        DIET, NORMAL, FAT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
