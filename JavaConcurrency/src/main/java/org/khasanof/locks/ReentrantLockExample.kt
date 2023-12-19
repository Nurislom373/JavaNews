package org.khasanof.locks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock

private val executor: ExecutorService = Executors.newFixedThreadPool(2)
private val lock: ReentrantLock = ReentrantLock()
private var count: Int = 0

fun main() {

    executor.submit {
        lock.lock()
        try {
            Thread.sleep(1000)
        } finally {
            lock.unlock()
        }
    }

    executor.submit {
        println("Locked: ${lock.isLocked}")
        println("Held by me: ${lock.isHeldByCurrentThread}")
        val tryLock = lock.tryLock()
        println("Lock acquired: $tryLock")
    }

    stop(executor)
}

private fun increment() {
    lock.lock()
    try {
        count += 1
    } finally {
        lock.unlock()
    }
}
