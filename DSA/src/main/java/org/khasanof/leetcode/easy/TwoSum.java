package org.khasanof.leetcode.easy;

/**
 * @author Nurislom
 * @see org.khasanof.leetcode.easy
 * @since 5/31/2025 3:16 PM
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        int[] result = twoSum(nums, 6);
        System.out.println("result = " + result);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] + nums[i + 1] == target) {
                result[0] = i;
                result[1] = i + 1;
                break;
            }
        }

        return result;
    }
}
