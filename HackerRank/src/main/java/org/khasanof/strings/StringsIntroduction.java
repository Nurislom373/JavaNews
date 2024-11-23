package org.khasanof.strings;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.strings
 * @since 11/23/2024 11:26 AM
 */
public class StringsIntroduction {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();

        System.out.println((A.length() + B.length()));
        System.out.println(A.compareTo(B) > 0 ? "Yes" : "No");
        System.out.println(capitalize(A) + " " + capitalize(B));
    }

    private static String capitalize(String var) {
        return var.substring(0, 1).toUpperCase() + var.substring(1);
    }
}
