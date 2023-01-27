package org.khasanof;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/27/2023
 * <br/>
 * Time: 6:18 PM
 * <br/>
 * Package: org.khasanof
 */
public class ParallelStreamTest {

    @Test
    void sequentialSumTest() {
        long sum = sequentialSum(12);
        System.out.println("sum = " + sum);
    }

    long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    long parallelSequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}
