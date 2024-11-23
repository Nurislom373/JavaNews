package org.khasanof.strings;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.strings
 * @since 11/23/2024 11:37 AM
 */
public class StringsSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();
        int start = in.nextInt();
        int end = in.nextInt();

        System.out.println(S.substring(start, end));
    }
}
