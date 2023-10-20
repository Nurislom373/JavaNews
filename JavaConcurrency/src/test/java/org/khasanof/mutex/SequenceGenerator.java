package org.khasanof.mutex;

/**
 * @author Nurislom
 * @see org.khasanof.thread_pools.mutex
 * @since 9/25/2023 10:42 PM
 */
public class SequenceGenerator {

    private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }

}
