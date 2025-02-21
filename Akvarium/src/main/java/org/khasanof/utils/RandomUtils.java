package org.khasanof.utils;

import java.util.Random;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/20/2025 12:40 AM
 */
public final class RandomUtils {

    private static final Random random = new Random();

    /**
     *
     * @return
     */
    public static int getRandomNumber() {
        return random.nextInt();
    }

    /**
     *
     * @param max
     * @return
     */
    public static int getRandomNumber(Integer max) {
        return getRandomNumber(2, max);
    }

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomNumber(Integer min, Integer max) {
        return random.nextInt(min, max);
    }
}
