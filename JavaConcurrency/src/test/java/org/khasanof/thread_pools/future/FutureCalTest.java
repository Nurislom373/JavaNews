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
    void futureGetMethodTest() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Integer> calculate = squareCalculator.calculate(5);

        Integer integer = calculate.get(2000, TimeUnit.MILLISECONDS);
        assertEquals(integer, 25);
    }

    @Test
    void futureCancelMethodTest() {
        Future<Integer> calculate = squareCalculator.calculate(5);

        boolean cancel = calculate.cancel(true);
        assertTrue(cancel);
    }

    @Test
    void multiFutureTest() throws InterruptedException, ExecutionException {
        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);

        while (!(future1.isDone() && future2.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println(result1 + " and " + result2);
    }



}
