package org.khasanof.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nurislom
 * @see org.khasanof.phaser
 * @since 4/18/2024 11:12 AM
 */
public class ParallelSummation {

    private final int[] array;
    private final int numThreads;
    private final Phaser phaser;
    private final AtomicInteger atomicInteger;

    ParallelSummation(int[] array, int numThreads) {
        this.array = array;
        this.numThreads = numThreads;
        this.phaser = new Phaser(numThreads);
        this.atomicInteger = new AtomicInteger(0);
    }

    public int computeSum() {
        int chunkSize = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? array.length : (i + 1) * chunkSize;

            new Thread(() -> {
                int localSum = 0;
                for (int j = start; j < end; j++) {
                    localSum += array[j];
                }
                System.out.println("Thread " + Thread.currentThread().getId() + ": Local sum = " + localSum);
                atomicInteger.getAndSet(atomicInteger.intValue() + localSum );
                phaser.arriveAndAwaitAdvance(); // Wait for other threads
            }).start();
        }

        phaser.awaitAdvance(phaser.getPhase()); // Wait for all threads to finish
        return atomicInteger.getAcquire(); // Compute the final sum here
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 4;

        ParallelSummation summation = new ParallelSummation(data, numThreads);
        int result = summation.computeSum();
        System.out.println("Total sum = " + result);
    }
}
