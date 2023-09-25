package org.khasanof.thread_pools.mutex

import java.util.concurrent.locks.ReentrantLock

class ReentrantLockSequenceGenerator : SequenceGenerator() {

    private val mutex = ReentrantLock()

    @Override
    override fun getNextSequence(): Int {
        try {
            mutex.lock()
            return super.getNextSequence()
        } finally {
            mutex.unlock()
        }
    }

}
