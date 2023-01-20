package org.khasanof;

import org.junit.jupiter.api.Test;
import org.khasanof.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/20/2023
 * <br/>
 * Time: 6:36 PM
 * <br/>
 * Package: org.khasanof
 */
public class StreamCollectorsTest {

    private final List<Dish> menu = List.of(
            new Dish("Osh", 500, 12000),
            new Dish("Shashlik", 700, 9000),
            new Dish("HotDog", 300, 17000),
            new Dish("Lavash", 400, 25000),
            new Dish("Burger", 400, 24000),
            new Dish("Gamburger", 500, 30000),
            new Dish("MurskoyKapriz", 500, 60000)
    );

    @Test
    void streamCollectorCountingMethodTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        Long count = stream.collect(Collectors.counting());
        System.out.println("count = " + count);
    }

    @Test
    void streamCollectorMaxByMethodTest() {
        Comparator<Dish> comparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = this.menu.stream()
                .collect(Collectors.maxBy(comparator));
        System.out.println(collect.isPresent());
    }

    @Test
    void streamCollectorSummingIntMethodTest() {
        Integer totalCalories = this.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));

        Double averageCalories = this.menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));

        System.out.println("totalCalories = " + totalCalories);
        System.out.println("averageCalories = " + averageCalories);
    }

}
