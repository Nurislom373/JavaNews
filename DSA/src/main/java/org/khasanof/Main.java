package org.khasanof;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i = 3 ^ 2;
        System.out.println("true = " + i);

        System.out.print("n ni kiriting: ");
        int n = scanner.nextInt();

        int k = 1;
        while (Math.pow(k, 2) <= n) {
            k++;
        }
        k--;

        System.out.println("Eng kichik k soni: " + k);
    }

    private static void truet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("n ni kiriting: ");
        int n = scanner.nextInt();

        int k = 1;
        while (Math.pow(k, 2) <= n) {
            k++;
        }

        System.out.println("Eng kichik k soni: " + k);
    }

    /*

        Birinchi ko'paytirish bajariladi.
     */
    // 17 = 3 * 5 + 2

    private static void numberSout(long num, int mod) {
        long result = 0;
        for (int i = 0; i < num; i++) {
            result += (num * i) / mod;
        }
        System.out.println("result = " + result);
    }

    private static Integer sum(int num) {
        int result = 0;
        for (int i = 0; i < num; i++) {
            result += i;
        }
        return result;
    }

    private static Integer gSum(int num) {
        int result = num;
        for (int i = 0; i < num; i++) {
            result += (result + result);
        }
        return result;
    }

    /*
        N - Natural Numbers
        Z - Integers
        Q - Rational Numbers
        R - Real Numbers

        The most important logical operators are ¬ (negation), ∧ (conjunction), ∨ (disjunction), ⇒ (implication) and ⇔ (equivalence).
     */
}