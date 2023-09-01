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
        long recursive = factorialTailRecursive(6);
        System.out.println("recursive = " + recursive);
    }

    private static void factorialUsingForLoop(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        System.out.println("fact = " + fact);
    }

    private static void factorialUsingStreams(int n) {
        long fact = LongStream.rangeClosed(1, n)
                .reduce(1, (x, y) -> x * y);
        System.out.println("fact = " + fact);
    }

    private static void factorialUsingRecursion(int n) {
        System.out.println(factorialUsingRecursionIntern(n));
    }

    private static long factorialUsingRecursionIntern(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialUsingRecursionIntern(n - 1);
    }

    private static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n - 1);
    }

}
