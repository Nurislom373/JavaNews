package org.khasanof;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list = numbers.stream()
                .map(n -> n * n).toList();

        System.out.println("list = " + list);
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

}
