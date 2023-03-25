package org.khasanof.logarithm;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/25/2023
 * <br/>
 * Time: 3:03 PM
 * <br/>
 * Package: org.khasanof.logarithm
 */
public class Logarithm {

    public static void main(String[] args) {
        int count = logCount(2, 32);
        System.out.println(count);
    }

    private static int logCount(int log, int num) {
        int count = 0;
        while (1 < num) {
            num = num / log;
            count++;
        }
        return count;
    }

}
