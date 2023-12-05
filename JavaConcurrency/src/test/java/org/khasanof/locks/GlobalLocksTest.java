package org.khasanof.locks;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

/**
 * @author Nurislom
 * @see org.khasanof.locks
 * @since 12/5/2023 8:31 AM
 */
public class GlobalLocksTest {

    private static final Logger log = LoggerFactory.getLogger(GlobalLocksTest.class);
    private final ExecutorService executor = Executors.newFixedThreadPool(4);
    private static final Map<String, List<String>> map = new ConcurrentHashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final StampedLock stampedLock = new StampedLock();
    private final Faker faker = Faker.instance();

    @BeforeEach
    void beforeEach() {
        map.put("test", new ArrayList<>());
    }

    @Test
    void firstTest() {
        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(1);
                map.put("foo", new ArrayList<>());
                log.info(() -> "I write foo");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
            }
        });

        executor.submit(readTask);
        executor.submit(readTask);
    }

    @Test
    void secondTestShouldSuccess() throws InterruptedException {
        executor.submit(readAndWriteTask);
        Thread.sleep(1000);
        executor.submit(readStampedLock);
    }

    Runnable readStampedLock = () -> {
        long readLock = stampedLock.readLock();
        try {
            List<String> strings = map.get("test");
            System.out.println("read = " + strings);
        } finally {
            stampedLock.unlock(readLock);
        }
    };

    Runnable readAndWriteTask = () -> {
        long stamp = stampedLock.readLock();
        try {
            List<String> strings = map.get("test");
            if (Objects.nonNull(strings)) {
                stamp = stampedLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) {
                    System.out.println("Could not convert to write lock");
                    stamp = stampedLock.writeLock();
                }
                strings.add(faker.name().fullName());
                System.out.println("write = " + strings);
            }
        } finally {
            stampedLock.unlock(stamp);
        }
    };

    Runnable readTask = () -> {
        lock.readLock().lock();
        try {
            log.info(() -> "I get foo");
            System.out.println(map.get("foo"));
            sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.readLock().unlock();
        }
    };

}
