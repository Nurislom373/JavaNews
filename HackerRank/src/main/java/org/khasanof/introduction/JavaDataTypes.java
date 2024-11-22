package org.khasanof.introduction;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.introduction
 * @since 11/22/2024 10:44 PM
 */
public class JavaDataTypes {

    public static void main(String[] argh) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {

            try {
                long x = sc.nextLong();
                System.out.println(x + " can be fitted in:");
                checkDatatypeCanBeFit(x);
            } catch (Exception e) {
                System.out.println(sc.next() + " can't be fitted anywhere.");
            }

        }
    }

    private static void checkDatatypeCanBeFit(long x) {
        if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) {
            System.out.println("* byte");
        }
        if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) {
            System.out.println("* short");
        }
        if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) {
            System.out.println("* int");
        }
        System.out.println("* long");
    }
}
