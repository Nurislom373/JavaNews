package org.khasanof;

import java.util.Random;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/20/2025 1:11 PM
 */
public final class EnumUtils {

    private static final Random RANDOM = new Random();

    /**
     *
     * @param enumClass
     * @return
     * @param <T>
     */
    public static <T extends Enum<T>> T getRandomEnumValue(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[RANDOM.nextInt(values.length)];
    }
}
