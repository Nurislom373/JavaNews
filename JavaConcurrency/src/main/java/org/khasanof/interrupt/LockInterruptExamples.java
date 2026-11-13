package org.khasanof.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Как interrupt() работает с synchronized и ReentrantLock.
 * <ul>
 *     <li><b>synchronized</b>: поток, который ждёт монитор (состояние BLOCKED), на interrupt() НЕ реагирует.
 *     Флаг просто ставится, а поток продолжает ждать, пока монитор не освободится.</li>
 *     <li><b>ReentrantLock.lock()</b> ведёт себя так же: на interrupt() не реагирует.</li>
 *     <li><b>lockInterruptibly()</b>, <b>tryLock(timeout)</b>, <b>Condition.await()</b>:
 *     на interrupt() реагируют и бросают InterruptedException.</li>
 * </ul>
 */
public class LockInterruptExamples {

    /** Что мы увидели после interrupt(): было ли исключение, получен ли лок, каков флаг. */
    public record Outcome(boolean interruptedExceptionThrown, boolean acquiredLock, boolean flagAfterAcquire) {
    }

    // ===================== synchronized =====================

    /**
     * synchronized: монитор занят. Второй поток ждёт его (BLOCKED) и мы его прерываем.
     * Когда монитор освободится, поток всё равно зайдёт внутрь, а флаг останется true.
     */
    public Outcome synchronizedBlockedIsNotInterruptible() throws InterruptedException {
        Object monitor = new Object();
        AtomicBoolean entered = new AtomicBoolean();
        AtomicBoolean flag = new AtomicBoolean();

        Thread waiter = new Thread(() -> {
            synchronized (monitor) {
                entered.set(true);
                flag.set(Thread.currentThread().isInterrupted());
            }
        });

        synchronized (monitor) {
            waiter.start();
            ThreadInterruptExamples.waitForState(waiter, Thread.State.BLOCKED);
            waiter.interrupt();                 // на BLOCKED поток это не действует
            Thread.sleep(100);
            if (waiter.getState() != Thread.State.BLOCKED) {
                throw new IllegalStateException("Поток должен был остаться в BLOCKED");
            }
        } // монитор освободился
        waiter.join();
        return new Outcome(false, entered.get(), flag.get());
    }

    /** А вот wait() внутри synchronized прервать можно. */
    public Outcome synchronizedWaitIsInterruptible() throws InterruptedException {
        Object monitor = new Object();
        AtomicBoolean thrown = new AtomicBoolean();
        AtomicBoolean reacquired = new AtomicBoolean();
        AtomicBoolean flag = new AtomicBoolean();

        Thread t = new Thread(() -> {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    thrown.set(true);
                    reacquired.set(Thread.holdsLock(monitor)); // перед исключением монитор снова берётся
                    flag.set(Thread.currentThread().isInterrupted());
                }
            }
        });
        t.start();
        ThreadInterruptExamples.waitUntilWaiting(t);
        t.interrupt();
        t.join();
        return new Outcome(thrown.get(), reacquired.get(), flag.get());
    }

    // ===================== ReentrantLock =====================

    /** lock(): поток ждёт занятый лок и на interrupt() не реагирует. Когда лок получен, флаг true. */
    public Outcome lockIsNotInterruptible() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        AtomicBoolean acquired = new AtomicBoolean();
        AtomicBoolean flag = new AtomicBoolean();

        Thread waiter = new Thread(() -> {
            lock.lock();
            try {
                acquired.set(true);
                flag.set(Thread.currentThread().isInterrupted());
            } finally {
                lock.unlock();
            }
        });

        lock.lock();
        try {
            waiter.start();
            ThreadInterruptExamples.waitUntilWaiting(waiter); // lock() внутри использует park => состояние WAITING
            waiter.interrupt();
            Thread.sleep(100);
            if (waiter.getState() != Thread.State.WAITING) {
                throw new IllegalStateException("Поток должен был продолжать ждать лок");
            }
        } finally {
            lock.unlock();
        }
        waiter.join();
        return new Outcome(false, acquired.get(), flag.get());
    }

    /** lockInterruptibly(): если прервать во время ожидания, будет InterruptedException, а лок не будет получен. */
    public Outcome lockInterruptiblyIsInterruptible() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        AtomicBoolean thrown = new AtomicBoolean();
        AtomicBoolean acquired = new AtomicBoolean();
        AtomicBoolean flag = new AtomicBoolean();

        Thread waiter = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                acquired.set(true);
                lock.unlock();
            } catch (InterruptedException e) {
                thrown.set(true);
                flag.set(Thread.currentThread().isInterrupted()); // false
            }
        });

        lock.lock();
        try {
            waiter.start();
            ThreadInterruptExamples.waitUntilWaiting(waiter);
            waiter.interrupt();
            waiter.join(); // лок всё ещё у нас, но waiter уже вышел по исключению
        } finally {
            lock.unlock();
        }
        return new Outcome(thrown.get(), acquired.get(), flag.get());
    }

    /** tryLock(timeout) тоже реагирует на interrupt(): не ждёт, пока истечёт таймаут. */
    public Outcome tryLockWithTimeoutIsInterruptible() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        AtomicBoolean thrown = new AtomicBoolean();
        AtomicBoolean acquired = new AtomicBoolean();

        Thread waiter = new Thread(() -> {
            try {
                acquired.set(lock.tryLock(30, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                thrown.set(true);
            }
        });

        lock.lock();
        try {
            waiter.start();
            ThreadInterruptExamples.waitUntilTimedWaiting(waiter);
            waiter.interrupt();
            waiter.join(5_000);
            if (waiter.isAlive()) {
                throw new IllegalStateException("tryLock(timeout) должен был отреагировать на interrupt");
            }
        } finally {
            lock.unlock();
        }
        return new Outcome(thrown.get(), acquired.get(), false);
    }

    /** Condition.await() при interrupt(): сначала поток снова берёт лок, потом летит InterruptedException. */
    public Outcome conditionAwaitIsInterruptible() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        AtomicBoolean thrown = new AtomicBoolean();
        AtomicBoolean holdsLock = new AtomicBoolean();

        Thread t = new Thread(() -> {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                thrown.set(true);
                holdsLock.set(lock.isHeldByCurrentThread()); // await() возвращает управление только с залоченным локом
            } finally {
                lock.unlock();
            }
        });
        t.start();
        ThreadInterruptExamples.waitUntilWaiting(t);
        t.interrupt();
        t.join();
        return new Outcome(thrown.get(), holdsLock.get(), false);
    }

    /** Даже после interrupt() лок нужно отпускать в finally, иначе он останется занятым навсегда. */
    public boolean lockReleasedAfterInterrupt() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });
        t.start();
        ThreadInterruptExamples.waitUntilTimedWaiting(t);
        t.interrupt();
        t.join();
        return !lock.isLocked();
    }
}
