package org.khasanof.locks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.locks.StampedLock
import kotlin.system.exitProcess

/**
 * @see org.khasanof.locks
 * @author Nurislom
 * @since 12/29/2023 9:22 PM
 */
private val executor: ExecutorService = Executors.newFixedThreadPool(2)
private var maps: Map<String, String> = HashMap()
private val lock: StampedLock = StampedLock()

fun main() {

    executor.submit {
        val writeLock = lock.writeLock()
        try {
            maps += maps.plus("foo" to "bar")
            Thread.sleep(1000)
        } finally {
            lock.unlockWrite(writeLock)
        }
    }

    val readTask = Runnable {
        val readLock = lock.readLock()
        try {
            println(maps["foo"])
            Thread.sleep(1000)
        } finally {
            lock.unlockRead(readLock)
        }
    }

    executor.submit(readTask)
    executor.submit(readTask)

    Thread.sleep(2000)
    exitProcess(200)
}