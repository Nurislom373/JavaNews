package org.khasanof;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Integer sum = gSum(3);
        System.out.println("sum = " + sum);
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