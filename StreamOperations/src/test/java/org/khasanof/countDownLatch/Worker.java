package org.khasanof.countDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Author: Nurislom
 * <br/>
 * Date: 22.05.2023
 * <br/>
 * Time: 22:00
 * <br/>
 * Package: org.khasanof.countDownLatch
 */
public class Worker implements Runnable {

    private List<String> output;
    private CountDownLatch countDownLatch;

    public Worker(List<String> output, CountDownLatch countDownLatch) {
        this.output = output;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        output.add("Counted Down");
        countDownLatch.countDown();
    }
}
