package org.khasanof.arithmetic_progression;

/**
 * Author: Nurislom
 * <br/>
 * Date: 11.06.2023
 * <br/>
 * Time: 14:05
 * <br/>
 * Package: org.khasanof.arithmetic_progression
 */
public class ArithmeticProgression {

    // 1, 2, 3, 4, 5
    public static void main(String[] args) {
        int sum = arithmeticProgressionFormula(7);
        System.out.println("sum = " + sum);

        int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7};

        int formula = arithmeticProgressionFormulaWithArray(nums);
        System.out.println("formula = " + formula);
    }


    private static int arithmeticProgressionFormula(int num) {
        return (num * (num + 1)) / 2;
    }

    private static int arithmeticProgressionFormulaWithArray(int[] nums) {
        return (nums.length * (nums[0] + nums[nums.length - 1])) / 2;
    }




}
