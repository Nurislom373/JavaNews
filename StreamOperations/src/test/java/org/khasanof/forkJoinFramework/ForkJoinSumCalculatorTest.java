package org.khasanof.forkJoinFramework;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/14/2023
 * <br/>
 * Time: 11:45 AM
 * <br/>
 * Package: org.khasanof.forkJoinFramework
 */
public class ForkJoinSumCalculatorTest {

    @Test
    void forkJoinSum() {
        long[] nums = LongStream.rangeClosed(1, 5_000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(nums);
        Long invoke = new ForkJoinPool().invoke(task);
        System.out.println("invoke = " + invoke);
    }
}
