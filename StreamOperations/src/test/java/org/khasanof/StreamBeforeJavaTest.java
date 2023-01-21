package org.khasanof;

import org.junit.jupiter.api.Test;
import org.khasanof.model.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/11/2023
 * <br/>
 * Time: 11:15 AM
 * <br/>
 * Package: org.khasanof
 */
public class StreamBeforeJavaTest {

    /**
     * Before Java 7
     */
    @Test
    public void beforeJava7Test() {
        List<Dish> menu = List.of(
                new Dish("Osh", 500, 12000, "Food"),
                new Dish("Shashlik", 700, 9000, "Food"),
                new Dish("HotDog", 300, 17000, "FastFood"),
                new Dish("Lavash", 400, 25000, "FastFood"),
                new Dish("Burger", 400, 24000, "FastFood"),
                new Dish("Gamburger", 500, 30000, "FastFood"),
                new Dish("MurskoyKapriz", 500, 60000, "Salt")
        );

        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 500) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish lowCaloricDish : lowCaloricDishes) {
            lowCaloricDishesName.add(lowCaloricDish.getName());
        }

        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);
    }

    /**
     * After Java 8
     */
    @Test
    public void afterJava8Test() {
        List<Dish> menu = List.of(
                new Dish("Osh", 500, 12000, "Food"),
                new Dish("Shashlik", 700, 9000, "Food"),
                new Dish("HotDog", 300, 17000, "FastFood"),
                new Dish("Lavash", 400, 25000, "FastFood"),
                new Dish("Burger", 400, 24000, "FastFood"),
                new Dish("Gamburger", 500, 30000, "FastFood"),
                new Dish("MurskoyKapriz", 500, 60000, "Salt")
        );

        List<String> list = menu.stream()
                .filter(f -> f.getCalories() < 500)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .toList();

        System.out.println("list = " + list);
    }

}
