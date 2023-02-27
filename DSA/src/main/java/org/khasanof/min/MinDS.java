package org.khasanof.min;

import org.khasanof.utils.BaseUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/27/2023
 * <br/>
 * Time: 11:00 AM
 * <br/>
 * Package: org.khasanof.min
 */
public class MinDS {

    public static void main(String[] args) {
        int[] randomNumbersArray = BaseUtils.getRandomNumbersArray(10, 50);
        BaseUtils.iterateArray(randomNumbersArray);
        minDsEnhancedFor(randomNumbersArray);
    }

    // Imperative Example
    public static void minDsImperative(int[] nums) {
        int MIN_VALUE = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (MIN_VALUE > nums[i]) {
                MIN_VALUE = nums[i];
            }
        }
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

    // Enhanced For Example
    public static void minDsEnhancedFor(int[] nums) {
        int MIN_VALUE = nums[0];
        for (int num : nums) {
            MIN_VALUE = Math.min(MIN_VALUE, num);
        }
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

    // Collections Example
    public static void minDsWithCollections(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        Integer min = Collections.min(list);
        System.out.println("min = " + min);
    }

    // Declarative Example
    public static void minDsDeclarative(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).reduce(Integer::min)
                .orElseThrow(RuntimeException::new);
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

    // Declarative Second Example
    public static void minDsDeclarativeSecond(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).reduce((left, right) -> left > right ? right : left)
                .orElseThrow(RuntimeException::new);
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

    // Declarative Third Example
    public static void minDsDeclarativeThird(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).reduce((left, right) -> Math.min(left, right))
                .orElseThrow(RuntimeException::new);
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

    // Declarative Thought Example
    public static void minDsDeclarativeThought(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).summaryStatistics().getMin();
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

    // Sort Example
    public static void minDsSort(int[] nums) {
        Arrays.sort(nums);
        int MIN_VALUE = nums[0];
        System.out.println("MIN_VALUE = " + MIN_VALUE);
    }

}
