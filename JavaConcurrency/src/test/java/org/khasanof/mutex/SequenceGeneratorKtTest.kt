package org.khasanof.mutex

import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class SequenceGeneratorKtTest {

    @Test
    fun noMutexObjectTest() {
        val count = 1000
        val uniqueSequence = getUniqueSequence(SequenceGenerator(), count)
        assertEquals(count, uniqueSequence.size)
    }

    @Test
    fun synchronizedMutexObjectTest() {
        val count = 1000
        val uniqueSequence = getUniqueSequence(SynchronizedSequenceGenerator(), count)
        assertEquals(count, uniqueSequence.size)
    }

    @Test
    fun reentrantLockMutexObjectTest() {
        val count = 1000
        val uniqueSequence = getUniqueSequence(ReentrantLockSequenceGenerator(), count)
        assertEquals(count, uniqueSequence.size)
    }

    private fun getUniqueSequence(generator: SequenceGenerator, count: Int): Set<Int> {
        val executors = Executors.newFixedThreadPool(8)
        val uniqueSequence = LinkedHashSet<Int>()
        val futures = ArrayList<Future<Int>>()

        for (i in 1..count) {
            futures.add(executors.submit(generator::getNextSequence))
        }

        for (value in futures) {
            uniqueSequence.add(value.get())
        }

        executors.awaitTermination(1, TimeUnit.SECONDS)
        executors.shutdown()

        return uniqueSequence
    }

}
