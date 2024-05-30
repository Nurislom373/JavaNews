package org.khasanof.threadPools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12.06.2023
 * <br/>
 * Time: 11:06
 * <br/>
 * Package: org.khasanof.thread_pools
 */
public class ThreadPoolDemoTest {

    @Test
    void threadPoolSimpleTest() throws InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        AtomicBoolean executed = new AtomicBoolean();
        executor.execute(() -> {
            System.out.println("Hello World");
            executed.set(true);
        });

        Thread.sleep(500);
        assertTrue(executed.get());
    }

}
