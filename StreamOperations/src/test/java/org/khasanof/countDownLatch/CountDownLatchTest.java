package org.khasanof.countDownLatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 22.05.2023
 * <br/>
 * Time: 21:55
 * <br/>
 * Package: org.khasanof.countDownLatch
 */
/*
    Essentially, by using a CountDownLatch we can cause a thread to block until other threads
    have completed a given task.
 */
public class CountDownLatchTest {

    @Test
    void whenParallelExecutionProcessing_thenMainThreadWillBlockUntilCompletion() throws InterruptedException {
        List<String> objects = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> list = Stream.generate(() -> new Thread(new Worker(objects, countDownLatch)))
                .limit(5)
                .toList();

        list.forEach(Thread::start);
        countDownLatch.await();
        objects.add("Latch Released");

        assertThat(objects.size()).isEqualTo(6);
        assertThat(objects)
                .containsExactly(
                        "Counted Down",
                        "Counted Down",
                        "Counted Down",
                        "Counted Down",
                        "Counted Down",
                        "Latch Released");
    }

}
