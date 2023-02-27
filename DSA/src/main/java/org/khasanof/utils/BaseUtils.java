package org.khasanof.utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/27/2023
 * <br/>
 * Time: 11:12 AM
 * <br/>
 * Package: org.khasanof.utils
 */
public class BaseUtils {

    public static int[] getRandomNumbersArray(int size, int bound) {
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Random().nextInt(bound);
        }
        return ints;
    }

    public static void iterateArray(int[] ints) {
        Arrays.stream(ints)
                .forEach(i -> System.out.print(" " + i));
        System.out.println(" ");
    }
}
