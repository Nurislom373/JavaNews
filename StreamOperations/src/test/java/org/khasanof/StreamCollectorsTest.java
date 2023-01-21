package org.khasanof;

import org.junit.jupiter.api.Test;
import org.khasanof.model.Dish;

import java.util.*;
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
            new Dish("Osh", 500, 12000, "Food"),
            new Dish("Shashlik", 700, 9000, "Food"),
            new Dish("HotDog", 300, 17000, "FastFood"),
            new Dish("Lavash", 400, 25000, "FastFood"),
            new Dish("Burger", 400, 24000, "FastFood"),
            new Dish("Gamburger", 500, 30000, "FastFood"),
            new Dish("MurskoyKapriz", 500, 60000, "Salt")
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
        System.out.println("totalCalories = " + totalCalories);

        Double averageCalories = this.menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println("averageCalories = " + averageCalories);

        IntSummaryStatistics statistics = this.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("statistics = " + statistics);
    }

    @Test
    void streamCollectorReducingMethodTest() {
        Integer totalCaloriesWithReducing = this.menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("totalCaloriesWithReducing = " + totalCaloriesWithReducing);

        Optional<Dish> mostCaloriesDish = this.menu.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println("mostCaloriesDish = " + mostCaloriesDish);

        Integer totalCaloriesOtherWay = this.menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("totalCaloriesOtherWay = " + totalCaloriesOtherWay);
    }

    /**
     * A common database operation is to group items in a set, based on one or more properties.
     * As you saw in the earlier transactions-currency-grouping example, this operation can be
     * cumbersome, verbose, and error-prone when implemented with an
     * imperative style. But it can be easily translated in a single, readable statement by
     * rewriting it in a more functional style as encouraged by Java 8. As a second example of
     * how this feature works, suppose you want to classify the dishes in the menu according
     * to their type, putting the ones containing meat in a group, the ones with fish in
     * another group, and all others in a third group. You can easily perform this task using
     * a collector returned by the Collectors.groupingBy factory method, as follows:
     */
    @Test
    void streamCollectorGroupingByMethodTest() {
        Map<String, List<Dish>> typeList = this.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println("typeList = " + typeList);

        enum CaloricLevel { DIET, NORMAL, FAT }

        Map<CaloricLevel, List<Dish>> caloricLevelListMap = this.menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println("caloricLevelListMap = " + caloricLevelListMap);

    }

    /**
     * Frequently after performing a grouping operation you may need to manipulate the
     * elements in each resulting group. Suppose, for example, that you want to filter only
     * the caloric dishes, let’s say the ones with more than 500 calories. You may argue that
     * in this case you could apply this filtering predicate before the grouping like the
     * following
     */
    @Test
    void streamCollectorGroupingByAndFilterMethodsTest() {
        //
        Map<String, List<Dish>> listMap = this.menu.stream()
                .filter(dish -> dish.getCalories() > 400)
                .collect(Collectors.groupingBy(Dish::getType));

        System.out.println("listMap = " + listMap);

        //
        Map<String, List<Dish>> listMap1 = this.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.filtering(dish -> dish.getCalories() > 400,
                                Collectors.toList())));

        System.out.println("listMap1 = " + listMap1);

        //
        Map<String, List<String>> dishNamesByType = this.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getName,
                                Collectors.toList())));

        System.out.println("dishNamesByType = " + dishNamesByType);
    }



}
