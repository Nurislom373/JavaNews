package org.khasanof.introduction;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.introduction
 * @since 11/22/2024 10:14 PM
 */
public class JavaLoops2 {

    public static void main(String[] argh) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            printNumber(a, b, n);
        }
        in.close();
    }

    private static void printNumber(int a, int b, int n) {
        for (int i = 1; i <= n; i++) {

            int printNumber = a;
            int multiNumber = 1;

            for (int j = 1; j <= i; j++) {
                printNumber += multiNumber * b;
                multiNumber += multiNumber;
            }

            System.out.print(printNumber + " ");
        }
        System.out.print("\n");
    }
}
