package org.khasanof;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/5/2023
 * <br/>
 * Time: 2:01 PM
 * <br/>
 * Package: org.khasanof
 */
public class SynchronizedCounter {

    private int sum = 0;

    public synchronized void counter() {
        setSum(getSum() + 1);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
