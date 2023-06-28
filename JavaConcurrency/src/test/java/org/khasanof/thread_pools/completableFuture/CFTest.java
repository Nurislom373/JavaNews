package org.khasanof.thread_pools.completableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 19.06.2023
 * <br/>
 * Time: 22:47
 * <br/>
 * Package: org.khasanof.thread_pools.completableFuture
 */
public class CFTest {

    @Test
    void suppleAsyncTest() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "hello completable future")
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println);

        assertTrue(voidCompletableFuture.isDone());
    }

    @Test
    void thenApplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
                .thenApply(s -> s + " World");

        assertEquals("Hello World", future.get());
    }

    @Test
    void thenRunTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
                .thenRun(() -> System.out.println("Computation finished."));

        System.out.println("Boom");
        future.get();
    }

}
