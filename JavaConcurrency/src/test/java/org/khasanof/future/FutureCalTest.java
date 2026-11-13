package org.khasanof.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

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
        Future<Integer> future3 = squareCalculator.calculate(1000);

        while (!(future1.isDone() && future2.isDone() && future3.isDone())) {
            System.out.printf(
                    "future1 is %s and future2 is %s and future3 is %s%n",
                    future1.isDone() ? "done" : "not done",
                    future2.isDone() ? "done" : "not done",
                    future3.isDone() ? "done" : "not done"
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();
        Integer result3 = future3.get();

        System.out.println(result1 + " and " + result2 + " and " + result3);
    }

    @Test
    void testRunnable() {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        Runnable task1 = () -> System.out.println("Task 1 executed by " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Task 2 executed by " + Thread.currentThread().getName());
        Runnable task3 = () -> System.out.println("Task 3 executed by " + Thread.currentThread().getName());
        Runnable task4 = () -> System.out.println("Task 4 executed by " + Thread.currentThread().getName());
        Runnable task5 = () -> System.out.println("Task 5 executed by " + Thread.currentThread().getName());
        Runnable task6 = () -> System.out.println("Task 6 executed by " + Thread.currentThread().getName());
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);
        executor.execute(task6);
        executor.shutdown();
    }
}
