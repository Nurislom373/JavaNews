package org.khasanof.mutex

class SynchronizedSequenceGenerator : SequenceGenerator() {

    @Synchronized
    override fun getNextSequence(): Int {
        return super.getNextSequence()
    }

}
