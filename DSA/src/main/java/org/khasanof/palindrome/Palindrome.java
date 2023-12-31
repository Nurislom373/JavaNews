package org.khasanof.palindrome;

/**
 * @author Nurislom
 * @see org.khasanof.palindrome
 * @since 12/31/2023 10:36 PM
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindromeNumber(121));
        System.out.println(isPalindromeNumber(1221));
        System.out.println(isPalindromeNumber(34));
        System.out.println(isPalindromeNumber(-121));
        System.out.println(isPalindromeNumber(0));
    }

    public static boolean isPalindromeNumber(int num) {
        return num >= 0 && num == reverseNumber(num);
    }

    public static int reverseNumber(int num) {
        int reversedNum = 0;
        while (num != 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num /= 10;
        }
        return reversedNum;
    }

}
