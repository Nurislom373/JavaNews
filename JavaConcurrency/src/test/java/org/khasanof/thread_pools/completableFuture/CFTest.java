package org.khasanof.thread_pools.completableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

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

        Assertions.assertTrue(voidCompletableFuture.isDone());
    }

}
