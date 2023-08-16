package org.khasanof.thread_pools.completableFuture;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.*;

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
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> "hello completable future").thenApply(String::toUpperCase).thenAccept(System.out::println);

        assertTrue(voidCompletableFuture.isDone());
    }

    @Test
    void thenApplyTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture.thenApply(s -> s + " World");

        assertEquals("Hello World", future.get());
    }

    @Test
    void thenRunTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));

        System.out.println("Boom");
        future.get();
    }

    @Test
    void handleTest() throws ExecutionException, InterruptedException {
        String name = null;

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        assertEquals("Hello, Stranger!", completableFuture.get());
    }

    @Test
    void combineTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;
        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();
        CompletableFuture<Integer> c = a.thenCombine(b, Integer::sum);
        executorService.submit(() -> a.complete(f(x)));
        executorService.submit(() -> b.complete(f(x)));
        System.out.println(c.get());
        executorService.shutdown();
        Assertions.assertEquals(c.get(), 3575138);
    }

    @SneakyThrows
    @Test
    void combineTest2() {
        CompletableFuture<Integer> combine = CompletableFuture.supplyAsync(() -> 5 * 5).thenCombine(CompletableFuture.supplyAsync(() -> 80), (price, rate) -> price + rate);
        Assertions.assertEquals(combine.get(), 105);
    }

    @SneakyThrows
    @Test
    void combineTest3() {
        CompletableFuture<Integer> combine = CompletableFuture.supplyAsync(() -> 5 * 5)
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return 100;
                }).orTimeout(100, TimeUnit.MILLISECONDS).handle((val, ex) -> {
                    if (Objects.nonNull(ex)) {
                        System.out.println("ex = " + ex);
                        return 50;
                    }
                    return val;
                }), (price, rate) -> price + rate);
        Assertions.assertEquals(combine.get(), 75);
    }

    @SneakyThrows
    @Test
    void combineTest4() {
        CompletableFuture<Integer> combine = CompletableFuture.supplyAsync(() -> 5 * 5)
                .thenCombine(CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return 100;
                }).completeOnTimeout(50, 100, TimeUnit.MILLISECONDS),
                        (price, rate) -> price + rate);
        Assertions.assertEquals(combine.get(), 75);
    }

    int f(int val) {
        return val * val;
    }

}
