package org.khasanof.mutex

import java.util.concurrent.Semaphore

class SemaphoreSequenceGenerator : SequenceGenerator() {

    private val mutex = Semaphore(1)

    @Override
    override fun getNextSequence(): Int {
        try {
            mutex.acquire()
            return super.getNextSequence()
        } catch (e: InterruptedException) {
            e.printStackTrace()
            throw e
        } finally {
            mutex.release()
        }
    }

}
