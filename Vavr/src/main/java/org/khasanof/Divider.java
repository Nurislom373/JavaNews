package org.khasanof;

import io.vavr.control.Try;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/13/2024 2:50 PM
 */
public abstract class Divider {

    /**
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        // throws if divisor is zero
        return dividend / divisor;
    }

    /**
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static Try<Integer> divideVavr(int dividend, int divisor) {
        return Try.of(() -> dividend / divisor);
    }
}
