package org.khasanof.visibility;

public class WithVolatileExample {

    static volatile boolean running = true;

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {
            while (running) {}
            System.out.println("Thread stopped!");
        });

        worker.start();

        Thread.sleep(1000);

        running = false;

        System.out.println("Main thread: running = false");
    }
}
