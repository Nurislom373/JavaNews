package org.khasanof;

import org.junit.jupiter.api.Test;
import org.khasanof.model.Dish;
import org.khasanof.model.Trader;
import org.khasanof.model.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/16/2023
 * <br/>
 * Time: 4:52 PM
 * <br/>
 * Package: org.khasanof
 */
public class StreamMapAndFlatMapTest {

    @Test
    void mapTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 11, 9);

        double average = numbers.stream()
                .mapToInt(i -> i)
                .average()
                .orElseThrow();
        System.out.println("average = " + average);

        List<Integer> list = numbers.stream()
                .map(n -> n * n)
                .toList();
        System.out.println("list = " + list);

        Integer max = numbers.stream()
                .reduce(Integer::max)
                .orElseThrow();
        System.out.println("max = " + max);

        Integer min = numbers.stream()
                .reduce(Integer::min)
                .orElseThrow();
        System.out.println("min = " + min);
    }

    @Test
    void flatMapTest() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .toList();

        System.out.println("pairs = " + pairs);
    }

    /**
     * Find all transactions in the year 2011 and sort them by value (small to high).
     */
    @Test
    void findTransactionSmallToHigh() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Finds all transactions in 2011 and sort by value (small to high)
        List<Transaction> tr2011 = transactions.stream().filter(o -> o.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();
        System.out.println("tr2011 = " + tr2011);

        //  What are all the unique cities where the traders work?
        List<String> cities = transactions.stream().map(tr -> tr.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println("cities = " + cities);

        /*
        You haven’t seen this yet, but you could also drop distinct() and use toSet() instead,
        which would convert the stream into a set.
         */
        Set<String> uniqueCities = transactions.stream().map(tr -> tr.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println("uniqueCities = " + uniqueCities);

        // Finds all traders from Cambridge and sort them by name
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        System.out.println("traders = " + traders);

        // What’s the highest value of all the transactions?
        Optional<Integer> optional = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println("optional.isPresent() = " + optional.isPresent());

        // Finds the transaction with the smallest value
        Optional<Transaction> reduce = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println("reduce.isPresent() = " + reduce.isPresent());

        /*
          A stream supports the methods min and max that take a Comparator
          as argument to specify which key to compare with when calculating the minimum
          or maximum
         */
        Optional<Transaction> minTransaction = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("minTransaction.isPresent() = " + minTransaction.isPresent());
    }

    @Test
    void caloriesTest() {
        List<Dish> menu = List.of(
                new Dish("Osh", 500, 12000, "Food"),
                new Dish("Shashlik", 700, 9000, "Food"),
                new Dish("HotDog", 300, 17000, "FastFood"),
                new Dish("Lavash", 400, 25000, "FastFood"),
                new Dish("Burger", 400, 24000, "FastFood"),
                new Dish("Gamburger", 500, 30000, "FastFood"),
                new Dish("MurskoyKapriz", 500, 60000, "Salt")
        );

        /*
        You saw earlier that you could use the reduce method to calculate the sum of the elements of a stream
         */
        Integer reduceCalories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("reduceCalories = " + reduceCalories);

        /*
        The most common methods you’ll use to convert a stream to a specialized version are
        mapToInt, mapToDouble, and mapToLong. These methods work exactly like the method
        map that you saw earlier but return a specialized stream instead of a Stream<T>. For
        example, you can use mapToInt as follows to calculate the sum of calories in the menu
         */
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("sum = " + sum);
    }



}
