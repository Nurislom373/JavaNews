package org.khasanof.multithreaded;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Nurislom
 * @see org.khasanof.multithreaded
 * @since 5/3/2024 10:29 PM
 */
public class MultiThreadedTest {

    @Test
    public void twoThreadsShouldFailEventually() throws InterruptedException {
        final ClassWithThreadingProblem classWithThreadingProblem = new ClassWithThreadingProblem();

        Runnable runnable = classWithThreadingProblem::takeNextId;

        for (int i = 0; i < 50000; i++) {
            int startingId = classWithThreadingProblem.takeNextId();
            int expectedResult = 2 + startingId;

            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            int endingId = classWithThreadingProblem.takeNextId();

            if (endingId != expectedResult)
                return;
        }

        fail("Should have exposed a threading issue but it did not.");
    }
}
