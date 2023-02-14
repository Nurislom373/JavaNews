package org.khasanof;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/5/2023
 * <br/>
 * Time: 8:29 AM
 * <br/>
 * Package: org.khasanof
 */
public class MultiTask {

    @Test
    void main() {
        Runnable runnable1 = () -> {
            System.out.println("true = " + true);
        };

        Runnable runnable2 = () -> {
            System.out.println("Lorem Ipsum");
        };

        Runnable runnable3 = () -> {
            System.out.println("Nigga");
        };

        Callable<String> callable = () -> {
            double random = Math.random();
            return String.valueOf(random);
        };

        List.of(callable, callable, callable);

        List<Runnable> list = List.of(runnable1, runnable2, runnable3);

        executorsInvokeAll(list);
    }

    public void process(Runnable runnable) {
        new Thread(runnable).start();
    }

    void executorsInvokeAll(List<Runnable> list) {
        ExecutorService service = Executors.newFixedThreadPool(4);
        List<? extends Future<?>> futures = list.stream().map(service::submit).toList();
        boolean anyMatch = futures.stream().anyMatch(any -> !any.isDone());
        if (anyMatch) {
            throw new RuntimeException("Task Failed");
        }
    }

    void executorsInvokeAll(List<Callable> list, boolean callable) {
        ExecutorService service = Executors.newFixedThreadPool(4);
    }

    void executors(Runnable runnable) {

    }

}
