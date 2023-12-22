package org.khasanof.locks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

private var executor: ExecutorService = Executors.newFixedThreadPool(2)
var map: Map<String, String> = mutableMapOf()
private var lock: ReadWriteLock = ReentrantReadWriteLock()

fun main() {

    executor.submit(writeTask)
    executor.submit(readTask)
    executor.submit(readTask)

    println("ForEach Map")

    map.forEach(System.out::println)

}

private val writeTask: Runnable = Runnable {
    lock.writeLock().lock()
    try {
        Thread.sleep(1000)
        val newPair = "foo" to "bar"
        map += map.plus(newPair)
    } finally {
        lock.writeLock().unlock()
    }
}

private val readTask: Runnable = Runnable {
    lock.readLock().lock()
    try {
        println(map["foo"])
    } finally {
        lock.readLock().unlock()
    }
}