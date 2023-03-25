package org.khasanof.fibonacci;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/25/2023
 * <br/>
 * Time: 12:01 PM
 * <br/>
 * Package: org.khasanof.fibonacci
 */
public class FibonacciDS {

    public static void main(String[] args) {
        fibonacciUsingRecursion(10);
    }

    public static void fibonacciUsingForLoop(int n) {
        int firstTerm = 0, secondTerm = 1;
        for (int i = 1; i <= n; i++) {
            System.out.print(firstTerm + ", ");

            // compute
            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }
    }

    public static void fibonacciUsingWhileLoop(int n) {
        int i = 1, firstTerm = 0, secondTerm = 1;
        while (i <= n) {
            System.out.print(firstTerm + ", ");

            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
            i++;
        }
    }

    public static void fibonacciUsingRecursion(int n) {
        System.out.print(fibonacciUsingRecursionIntern(n));
    }

    private static int fibonacciUsingRecursionIntern(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacciUsingRecursionIntern(n - 1) + fibonacciUsingRecursionIntern(n - 2);
    }

}
