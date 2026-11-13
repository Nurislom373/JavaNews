package org.khasanof.interrupt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Примеры работы с Thread.interrupt().
 * <p>
 * Главное, что нужно запомнить:
 * <ol>
 *     <li>interrupt() только ставит флаг "прерван" в true. Поток он сам НЕ останавливает.</li>
 *     <li>Блокирующие методы (sleep, wait, join) видят этот флаг, бросают InterruptedException
 *     и сбрасывают флаг обратно в false.</li>
 *     <li>isInterrupted() только читает флаг, а статический Thread.interrupted() читает и сбрасывает его.</li>
 * </ol>
 */
public class ThreadInterruptExamples {

    /** Результат: что произошло, когда мы прервали спящий поток. */
    public record SleepResult(boolean interruptedExceptionThrown, boolean flagAfterCatch) {
    }

    /** 1) Прерываем поток во время sleep(): летит InterruptedException, флаг сбрасывается. */
    public SleepResult interruptSleepingThread() throws InterruptedException {
        AtomicBoolean thrown = new AtomicBoolean();
        AtomicBoolean flag = new AtomicBoolean();
        CountDownLatch started = new CountDownLatch(1);

        Thread t = new Thread(() -> {
            try {
                started.countDown();
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                thrown.set(true);
                flag.set(Thread.currentThread().isInterrupted()); // false: исключение уже сбросило флаг
            }
        });
        t.start();
        started.await();
        waitUntilTimedWaiting(t); // ждём, пока поток точно уснёт
        t.interrupt();
        t.join();
        return new SleepResult(thrown.get(), flag.get());
    }

    /** 2) Если прервать поток ДО sleep(), то sleep() сразу бросит исключение. */
    public boolean interruptBeforeSleep() {
        Thread.currentThread().interrupt();
        try {
            Thread.sleep(10_000);
            return false;
        } catch (InterruptedException e) {
            return true;
        } finally {
            Thread.interrupted(); // сбрасываем флаг, чтобы не влиять на другие тесты
        }
    }

    /**
     * 3) Обычный цикл без блокировок: interrupt() просто ставит флаг.
     * Цикл должен сам проверять флаг, иначе он не остановится.
     * @return значение isInterrupted() после выхода из цикла (true - флаг остался)
     */
    public boolean cooperativeCancellation() throws InterruptedException {
        AtomicLong counter = new AtomicLong();
        AtomicBoolean flagAfterLoop = new AtomicBoolean();
        CountDownLatch running = new CountDownLatch(1);

        Thread t = new Thread(() -> {
            running.countDown();
            while (!Thread.currentThread().isInterrupted()) {
                counter.incrementAndGet();
            }
            flagAfterLoop.set(Thread.currentThread().isInterrupted());
        });
        t.start();
        running.await();
        t.interrupt();
        t.join();
        return flagAfterLoop.get();
    }

    /** 4) Если поток не проверяет флаг, interrupt() его не остановит. Возвращает: жив ли поток после interrupt(). */
    public boolean ignoredInterruptKeepsRunning() throws InterruptedException {
        AtomicBoolean stop = new AtomicBoolean();
        CountDownLatch running = new CountDownLatch(1);

        Thread t = new Thread(() -> {
            running.countDown();
            while (!stop.get()) {
                Thread.onSpinWait(); // флаг прерывания вообще не проверяется
            }
        });
        t.start();
        running.await();
        t.interrupt();
        Thread.sleep(100);
        boolean stillAlive = t.isAlive();
        stop.set(true); // останавливаем поток своим собственным флагом
        t.join();
        return stillAlive;
    }

    /** 5) isInterrupted() оставляет флаг как есть, а Thread.interrupted() сбрасывает его. */
    public boolean[] isInterruptedVsInterrupted() {
        Thread.currentThread().interrupt();
        boolean first = Thread.currentThread().isInterrupted();   // true, флаг остался
        boolean second = Thread.currentThread().isInterrupted();  // true, всё ещё остался
        boolean third = Thread.interrupted();                     // true, но теперь флаг сброшен
        boolean fourth = Thread.interrupted();                    // false, флага уже нет
        return new boolean[]{first, second, third, fourth};
    }

    /** 6) Плохая практика: если "проглотить" исключение, информация о прерывании теряется. Возвращает флаг. */
    public boolean swallowedInterruptIsLost() throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean(true);
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException ignored) {
                // исключение проглочено - флаг потерян
            }
            flag.set(Thread.currentThread().isInterrupted());
        });
        t.start();
        waitUntilTimedWaiting(t);
        t.interrupt();
        t.join();
        return flag.get();
    }

    /** 7) Хорошая практика: в catch заново ставим флаг, чтобы код выше тоже узнал о прерывании. Возвращает флаг. */
    public boolean restoredInterruptIsPreserved() throws InterruptedException {
        AtomicBoolean flag = new AtomicBoolean();
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // восстанавливаем флаг
            }
            flag.set(Thread.currentThread().isInterrupted());
        });
        t.start();
        waitUntilTimedWaiting(t);
        t.interrupt();
        t.join();
        return flag.get();
    }

    /** 8) join() тоже можно прервать: поток, который ждёт, получит InterruptedException. */
    public boolean interruptWhileJoining() throws InterruptedException {
        AtomicBoolean thrown = new AtomicBoolean();
        Thread longRunning = new Thread(() -> {
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException ignored) {
            }
        });
        longRunning.setDaemon(true);
        longRunning.start();

        Thread joiner = new Thread(() -> {
            try {
                longRunning.join();
            } catch (InterruptedException e) {
                thrown.set(true);
            }
        });
        joiner.start();
        waitUntilWaiting(joiner);
        joiner.interrupt();
        joiner.join();
        longRunning.interrupt(); // убираем за собой долгий поток
        return thrown.get();
    }

    /** 9) Object.wait() тоже реагирует на interrupt(). */
    public boolean interruptWaitingThread() throws InterruptedException {
        Object monitor = new Object();
        AtomicBoolean thrown = new AtomicBoolean();
        Thread t = new Thread(() -> {
            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    thrown.set(true);
                }
            }
        });
        t.start();
        waitUntilWaiting(t);
        t.interrupt();
        t.join();
        return thrown.get();
    }

    // ---- вспомогательные методы ----

    static void waitUntilTimedWaiting(Thread t) throws InterruptedException {
        waitForState(t, Thread.State.TIMED_WAITING);
    }

    static void waitUntilWaiting(Thread t) throws InterruptedException {
        waitForState(t, Thread.State.WAITING);
    }

    /** Ждёт (до 5 секунд), пока поток не окажется в нужном состоянии. Так тесты не зависят от скорости машины. */
    static void waitForState(Thread t, Thread.State state) throws InterruptedException {
        long deadline = System.nanoTime() + 5_000_000_000L;
        while (t.getState() != state) {
            if (System.nanoTime() > deadline) {
                throw new IllegalStateException("Поток не перешёл в состояние " + state + ", сейчас: " + t.getState());
            }
            Thread.sleep(5);
        }
    }
}
