package org.khasanof.condition;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBoundedBuffer<V> {

    private final Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private final Queue<V> queue = new ArrayDeque<>();

    private final int capacity;

    public ConditionBoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void put(V value) throws InterruptedException {
        lock.lock();

        try {
            while (queue.size() == capacity) {
                notFull.await();
            }

            queue.add(value);

            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public V take() throws InterruptedException {
        lock.lock();

        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }

            V value = queue.remove();

            notFull.signal();

            return value;

        } finally {
            lock.unlock();
        }
    }
}