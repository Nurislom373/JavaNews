package org.khasanof.factorial;

import java.util.stream.LongStream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/27/2023
 * <br/>
 * Time: 12:04 PM
 * <br/>
 * Package: org.khasanof.factorial
 */
public class FactorialDS {

    public static void main(String[] args) {
        factorialUsingStreams(9);
    }

    private static void factorialUsingForLoop(int n) {
        long fact = 1;
        for (int i = 2; i < n; i++) {
            fact = fact * i;
        }
        System.out.println("fact = " + fact);
    }

    private static void factorialUsingStreams(int n) {
        long fact = LongStream.rangeClosed(1, n)
                .reduce(1, (x, y) -> x * y);
        System.out.println("fact = " + fact);
    }

    private static long factorialUsingRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialUsingRecursion(n - 1);
    }

}
