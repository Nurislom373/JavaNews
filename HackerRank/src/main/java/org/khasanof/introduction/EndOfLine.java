package org.khasanof.introduction;

import java.util.Scanner;

/**
 * @author Nurislom
 * @see org.khasanof.introduction
 * @since 11/22/2024 10:51 PM
 */
public class EndOfLine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countLine = 1;
        while (scanner.hasNext()) {
            System.out.println(countLine++ + " " + scanner.nextLine());
        }
    }
}
