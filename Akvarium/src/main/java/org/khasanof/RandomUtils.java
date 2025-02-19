package org.khasanof;

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
        return random.nextInt(2, 10);
    }
}
