package org.khasanof.locks;

import com.github.javafaker.Faker;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * @author Nurislom
 * @see org.khasanof.locks
 * @since 12/5/2023 9:17 AM
 */
public class SharedObjectRW {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private Object sharedData;

    public void useObject() {
        lock.readLock().lock();
        try {
            // Perform read operation on the shared object
            System.out.println(Thread.currentThread().getName() + " is reading: " + sharedData);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void writeObject(Object newData) {
        lock.writeLock().lock();
        try {
            // Perform write operation on the shared object
            sharedData = newData;
            System.out.println(Thread.currentThread().getName() + " is writing: " + newData);
        } finally {
            lock.writeLock().unlock();
        }
    }

}

class SharedObjectRWTest {

    public static void main(String[] args) {
        Faker faker = Faker.instance();
        SharedObjectRW sharedObject = new SharedObjectRW();

        IntStream.range(0, 100)
                .forEach(i -> {
                    if (i % 2 == 0) {
                        new Thread(() -> sharedObject.writeObject(faker.name().fullName()), "WriterThread").start();
                    } else {
                        new Thread(sharedObject::useObject, "ReaderThread-" + i).start();
                    }
                });

//        // Multiple reader threads
//        for (int i = 1; i <= 3; i++) {
//            new Thread(sharedObject::useObject, "ReaderThread-" + i).start();
//        }
//
//        // Writer thread
//        new Thread(() -> {
//            sharedObject.writeObject("New Data");
//        }, "WriterThread").start();
//
//        // Multiple reader threads
//        for (int i = 1; i <= 3; i++) {
//            new Thread(sharedObject::useObject, "ReaderThread-" + i).start();
//        }
    }

}
