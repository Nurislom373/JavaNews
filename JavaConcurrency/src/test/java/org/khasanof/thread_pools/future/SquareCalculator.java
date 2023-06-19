package org.khasanof.thread_pools.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: Nurislom
 * <br/>
 * Date: 14.06.2023
 * <br/>
 * Time: 17:39
 * <br/>
 * Package: org.khasanof.thread_pools.future
 */
public class SquareCalculator {

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        return service.submit(() -> {
            try {
                Thread.sleep(1000);
                return input * input;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
