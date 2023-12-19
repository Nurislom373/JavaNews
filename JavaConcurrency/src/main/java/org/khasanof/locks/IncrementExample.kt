package org.khasanof.locks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.stream.IntStream

private var count: Int = 0

fun main() {

    val executorService = Executors.newFixedThreadPool(8)

    IntStream.range(0, 10000)
        .forEach {
            executorService.submit { increment() }
        }

    stop(executorService)

    println(count)
}

fun stop(executor: ExecutorService) {
    try {
        executor.shutdown()
        executor.awaitTermination(60, TimeUnit.SECONDS)
    } catch (e: InterruptedException) {
        System.err.println("termination interrupted")
    } finally {
        if (!executor.isTerminated) {
            System.err.println("killing non-finished tasks")
        }
        executor.shutdownNow()
    }
}

private fun increment() {
    count += 1
}

