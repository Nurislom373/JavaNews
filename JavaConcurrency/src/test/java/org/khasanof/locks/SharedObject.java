package org.khasanof.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nurislom
 * @see org.khasanof.locks
 * @since 12/5/2023 9:07 AM
 */
public class SharedObject {

    private final Lock lock = new ReentrantLock();
    private final Condition objectInUse = lock.newCondition();
    private Object sharedData;

    public void useObject() {
        lock.lock();
        try {
            while (sharedData != null) {
                try {
                    // Wait for other thread to finish using the object
                    objectInUse.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            // Perform work on the shared object
            // ...

            // Set sharedData to indicate that the object is in use
            sharedData = new Object();
        } finally {
            lock.unlock();
        }
    }

    public void releaseObject() {
        lock.lock();
        try {
            // Release the object
            sharedData = null;

            // Signal waiting threads that the object is available
            objectInUse.signal();
        } finally {
            lock.unlock();
        }
    }

}
