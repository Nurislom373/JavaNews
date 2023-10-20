package org.khasanof.countDownLatch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Nurislom
 * @see org.khasanof.countDownLatch
 * @since 10/20/2023 11:17 PM
 */
public class Worker implements Runnable {

    private final List<String> outputScraper;
    private final CountDownLatch countDownLatch;

    public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Do Some Work!");
        outputScraper.add("Counted down");
        countDownLatch.countDown();
    }
}
