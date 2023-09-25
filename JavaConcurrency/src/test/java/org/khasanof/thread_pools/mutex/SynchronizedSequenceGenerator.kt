package org.khasanof.thread_pools.mutex

class SynchronizedSequenceGenerator : SequenceGenerator() {

    @Synchronized
    override fun getNextSequence(): Int {
        return super.getNextSequence()
    }

}
