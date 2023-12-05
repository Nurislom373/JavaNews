package org.khasanof.locks;

import org.junit.jupiter.api.Test;

/**
 * @author Nurislom
 * @see org.khasanof.locks
 * @since 12/5/2023 9:07 AM
 */
public class SharedObjectTest {

    @Test
    void firstTest() {

        SharedObject sharedObject = new SharedObject();

        // Thread 1
        new Thread(() -> {
            sharedObject.useObject();
            System.out.println("Thread 1 is using the shared object");
            try {
                Thread.sleep(200); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sharedObject.releaseObject();
            System.out.println("Thread 1 released the shared object");
        }).start();

        // Thread 2
        new Thread(() -> {
            sharedObject.useObject();
            System.out.println("Thread 2 is using the shared object");
            try {
                Thread.sleep(300); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sharedObject.releaseObject();
            System.out.println("Thread 2 released the shared object");
        }).start();

        // Thread 3
        new Thread(() -> {
            sharedObject.useObject();
            System.out.println("Thread 3 is using the shared object");
            // No sleep to simulate quicker work
            sharedObject.releaseObject();
            System.out.println("Thread 3 released the shared object");
        }).start();

    }

}
