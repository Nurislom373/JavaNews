package org.khasanof.flow;

import java.util.Random;

/**
 * @author Nurislom
 * @see org.khasanof.flow
 * @since 8/29/2023 6:28 AM
 */
public class TempInfo {

    public static final Random RANDOM = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town) {
        if (RANDOM.nextInt(10) == 0) {
            throw new RuntimeException("Error!");
        }
        return new TempInfo(town, RANDOM.nextInt(100));
    }

    @Override
    public String toString() {
        return "TempInfo{" +
                "town='" + town + '\'' +
                ", temp=" + temp +
                '}';
    }
}
