package org.khasanof.phaser;

import java.util.concurrent.Phaser;

/**
 * @author Nurislom
 * @see org.khasanof.phaser
 * @since 4/18/2024 10:52 AM
 */
public class PhaserExample {

    public static void main(String[] args) {
        Phaser myPhaser = new Phaser();
        myPhaser.register();

        System.out.println("let's start phaser example");

        new PhaserRunner(myPhaser, "cat");
        new PhaserRunner(myPhaser, "dog");
        new PhaserRunner(myPhaser, "elephant");

        myPhaser.arriveAndAwaitAdvance();

        System.out.println("Ending phase one");

        myPhaser.arriveAndAwaitAdvance();

        System.out.println("Ending phase two");

        myPhaser.arriveAndAwaitAdvance();

        System.out.println("Ending phase three");
    }

    static class PhaserRunner implements Runnable {

        private final Phaser phaser;
        private final String threadName;

        public PhaserRunner(Phaser phaser, String threadName) {
            this.phaser = phaser;
            this.threadName = threadName;
            phaser.register();
            new Thread(this).start();
        }

        @Override
        public void run() {
            // phase 1 of our code.
            System.out.println("This is Phase one for : " + this.threadName);

            // creating a phaser barrier for all threads to sync
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(99);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // start new phase of execution, phase 2 of code
            System.out.println("This is Phase two for : " + this.threadName);

            // creating a barrier for all threads to sync
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(99);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // start new phase of execution, phase 3 of code
            System.out.println("This is Phase three for : " + this.threadName);

            phaser.arriveAndDeregister();
        }
    }
}
