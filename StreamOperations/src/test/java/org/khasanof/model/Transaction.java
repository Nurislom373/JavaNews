package org.khasanof.model;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/17/2023
 * <br/>
 * Time: 9:49 AM
 * <br/>
 * Package: org.khasanof.model
 */
public class Transaction {
    private final Trader trader;

    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
