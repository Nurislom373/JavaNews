package org.khasanof;

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

}
