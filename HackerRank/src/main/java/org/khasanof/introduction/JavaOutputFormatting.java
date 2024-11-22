package org.khasanof.introduction;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.introduction
 * @since 11/22/2024 9:33 PM
 */
public class JavaOutputFormatting {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("================================");
        for (int i = 0; i < 3; i++) {
            String s1 = sc.next();
            int x = sc.nextInt();
            // Complete this line
            System.out.printf("%-15s%03d%n", s1, x);

        }
        System.out.println("================================");
    }
}
