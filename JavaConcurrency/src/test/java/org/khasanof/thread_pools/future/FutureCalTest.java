package org.khasanof.thread_pools.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 14.06.2023
 * <br/>
 * Time: 17:49
 * <br/>
 * Package: org.khasanof.thread_pools.future
 */
public class FutureCalTest {

    private final SquareCalculator squareCalculator = new SquareCalculator();

    @Test
    void testRun() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> calculate = squareCalculator.calculate(5);

        Integer integer = calculate.get(2000, TimeUnit.MILLISECONDS);
        assertEquals(integer, 25);
    }

}
