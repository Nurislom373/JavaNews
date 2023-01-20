package org.khasanof;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/20/2023
 * <br/>
 * Time: 9:31 AM
 * <br/>
 * Package: org.khasanof
 */
public class IntStreamTest {

    /**
     * Numeric ranges
     * A common use case when dealing with numbers is working with ranges of numeric values.
     * For example, suppose you’d like to generate all numbers between 1 and 100. Java
     * 8 introduces two static methods available on IntStream and LongStream to help generate such ranges:
     * range and rangeClosed. Both methods take the starting value of
     * the range as the first parameter and the end value of the range as the second parameter. But range is exclusive,
     * whereas rangeClosed is inclusive.
     */
    @Test
    void intStreamRangeClosedTest() {
        IntStream stream = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);

        long count = stream.count();

        System.out.println(count);
        Assertions.assertEquals(count, 50);
    }

    @Test
    void streamNIOFileTest() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("teenWolfOverview.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("uniqueWords = " + uniqueWords);
    }

}
