package org.khasanof;

import org.assertj.core.util.TriFunction;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/8/2023
 * <br/>
 * Time: 12:27 PM
 * <br/>
 * Package: org.khasanof
 */
public class FunctionalInterfacesTest {


    /**
     * The java.util.function.Predicate<T> interface defines an abstract method named
     * test that accepts an object of generic type T and returns a boolean. It’s exactly the
     * same one that you created earlier, but it’s available out of the box! You might want to
     * use this interface when you need to represent a boolean expression that uses an
     * object of type T. For example, you can define a lambda that accepts String objects, as
     * shown in the following listing.
     */
    @Test
    public void sizeGreaterThanFiveLambda() {
        List<String> listOfStrings = List.of("Scott", "Allison", "Stiles", "Liam", "Lydia", "Derek", "Jackson");
        System.out.println("listOfStrings = " + listOfStrings);

        Predicate<String> sizeGreaterThanFive = (String s) -> s.length() > 5;
        List<String> filter = filter(listOfStrings, sizeGreaterThanFive);
        System.out.println("filter = " + filter);
    }

    private <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * The java.util.function.Consumer<T> interface defines an abstract method named
     * accept that takes an object of generic type T and returns no result (void). You might
     * use this interface when you need to access an object of type T and perform some operations on it.
     * For example, you can use it to create a method forEach, which takes a list
     * of Integers and applies an operation on each element of that list. In the following
     * listing, you’ll use this forEach method combined with a lambda to print all the elements of the list.
     */
    @Test
    public void showListItemWithLambda() {
        List<String> listOfStrings = List.of("Scott", "Allison", "Stiles", "Liam", "Lydia", "Derek", "Jackson");
        System.out.println("listOfStrings = " + listOfStrings);

//        forEach(listOfStrings, System.out::println); New Version!
        forEach(listOfStrings, (String var) -> System.out.println(var));
    }

    private <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    /**
     * The java.util.function.Function<T, R> interface defines an abstract method
     * named apply that takes an object of generic type T as input and returns an object of
     * generic type R. You might use this interface when you need to define a lambda that
     * maps information from an input object to an output (for example, extracting the
     * weight of an apple or mapping a string to its length). In the listing that follows, we
     * show how you can use it to create a method map to transform a list of Strings into a list
     * of Integers containing the length of each String.
     */
    @Test
    public void mapStringListToStringSizeList() {
        List<Integer> map = map(Arrays.asList("Scott", "Allison", "Stiles"), String::length);
        System.out.println("map = " + map);
    }

    private <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }


    /**
     * This method is the first method to read a file through lambda expression.
     */
    @Test
    public void fileReaderWithLambda() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/teenWolfCharacter.txt"));
            BufferedReaderProcessor processor = (BufferedReader br) -> br.readLine();
            String line;
            while ((line = processor.process(reader)) != null) {
                System.out.println("line = " + line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {

        String process(BufferedReader reader) throws IOException;

    }

    /**
     * This method is the second method to read a file through lambda expression.
     * <p>
     * Using local variables
     * All the lambda expressions we’ve shown so far used only their arguments inside their
     * body. But lambda expressions are also allowed to use free variables (variables that aren’t
     * the parameters and are defined in an outer scope) like anonymous classes can.
     * They’re called capturing lambdas. For example, the following lambda captures the variable reader:
     */
    @Test
    public void fileReaderWithBiFunction() {
        try (BufferedReader reader = new BufferedReader(new
                FileReader("src/test/resources/teenWolfCharacter.txt"))) {

            Function<BufferedReader, String> readerStringFunction =
                    (BufferedReader rd) -> {
                        try {
                            return rd.readLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    };

            String line;
            while ((line = readerStringFunction.apply(reader)) != null) {
                System.out.println("line = " + line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * In the following code, each element of a List of Integers is passed to the method
     * of String using a similar map method we defined earlier, resulting in a List of Strings
     * with various numbers:
     */
    @Test
    public void newInstanceCreateTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> list = newMap(numbers, Integer::toHexString);
        System.out.println("list = " + list);
    }

    public List<String> newMap(List<Integer> integers, Function<Integer, String> function) {
        List<String> strings = new ArrayList<>();
        for (Integer integer : integers) {
            strings.add(function.apply(integer));
        }
        return strings;
    }


    /**
     * Finally, you can also compose lambda expressions represented by the Function interface.
     * The Function interface comes with two default methods for this, andThen and
     * compose, which both return an instance of Function.
     * <p>
     * The method andThen returns a function that first applies a given function to an
     * input and then applies another function to the result of that application.
     * For example, given a function f that increments a number (x -> x + 1) and another function g
     * that multiples a number by 2, you can combine them to create a function h that first
     * increments a number and then multiplies the result by 2:
     */
    @Test
    public void mathGOFFunctionalInterface() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        /*
         * You can also use the method compose similarly to first apply the function given as argument
         * to compose and then apply the function to the result. For example, in the previous
         * example using compose, it would mean f(g(x)) instead of g(f(x)) using andThen
         */
        // andThen method x + 1 then x * 2
        Function<Integer, Integer> andThen = f.andThen(g);
        // compose method x * 2 then x + 1
        Function<Integer, Integer> compose = f.compose(g);

        Integer andThenResult = andThen.apply(1);
        Integer composeResult = compose.apply(1);

        // This return 4.
        System.out.println("andThenResult = " + andThenResult);

        // This return 3.
        System.out.println("composeResult = " + composeResult);
    }

    @Test
    public void supplierFunctionalInterfaceTest() {
        List<Integer> collect = IntStream.rangeClosed(1, 10)
                .mapToObj((m) -> getValue(() -> m % 2 == 1 ? m : 0))
                .peek(System.out::println)
                .toList();

        assertEquals(collect.size(), 10);
    }

    private Integer getValue(Supplier<Integer> supplier) {
        return supplier.get();
    }

    @Test
    void triFunctionTest() {
        TriFunction<Character, Integer, Integer, Integer> function = this::getValue;

        assertEquals(25, function.apply('*', 5, 5));
    }

    private Integer getValue(Character oper, Integer var1, Integer var2) {
        return switch (oper) {
            case '+' -> var1 + var2;
            case '-' -> var1 - var2;
            case '*' -> var1 * var2;
            default -> throw new RuntimeException();
        };
    }

}
