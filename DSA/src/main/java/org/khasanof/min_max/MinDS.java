package org.khasanof.min_max;

import org.khasanof.utils.BaseUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
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
        minAndMaxDsDeclarativeThought(randomNumbersArray);
    }

    // Imperative Example
    public static void minAndMaxDsImperative(int[] nums) {
        int MIN_VALUE = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (MIN_VALUE > nums[i]) {
                MIN_VALUE = nums[i];
            }
        }
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (MAX_VALUE < nums[i]) {
                MAX_VALUE = nums[i];
            }
        }
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

    // Enhanced For Example
    public static void minAndMaxDsEnhancedFor(int[] nums) {
        int MIN_VALUE = nums[0];
        for (int num : nums) {
            MIN_VALUE = Math.min(MIN_VALUE, num);
        }
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = nums[0];
        for (int num : nums) {
            MAX_VALUE = Math.max(MAX_VALUE, num);
        }
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

    // Collections Example
    public static void minAndMaxDsWithCollections(int[] nums) {
        List<Integer> list1 = Arrays.stream(nums).boxed().toList();
        Integer min = Collections.min(list1);
        System.out.println("min = " + min);

        List<Integer> list2 = Arrays.stream(nums).boxed().toList();
        Integer max = Collections.min(list2);
        System.out.println("max = " + max);
    }

    // Declarative Example
    public static void minAndMaxDsDeclarative(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).reduce(Integer::min)
                .orElseThrow(RuntimeException::new);
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = Arrays.stream(nums).reduce(Integer::max)
                .orElseThrow(RuntimeException::new);
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

    // Declarative Second Example
    public static void minAndMaxDsDeclarativeSecond(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).reduce((left, right) -> left > right ? right : left)
                .orElseThrow(RuntimeException::new);
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = Arrays.stream(nums).reduce((left, right) -> left < right ? right : left)
                .orElseThrow(RuntimeException::new);
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

    // Declarative Third Example
    public static void minAndMaxDsDeclarativeThird(int[] nums) {
        int MIN_VALUE = Arrays.stream(nums).reduce((left, right) -> Math.min(left, right))
                .orElseThrow(RuntimeException::new);
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = Arrays.stream(nums).reduce((left, right) -> Math.max(left, right))
                .orElseThrow(RuntimeException::new);
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

    // Declarative Thought Example
    public static void minAndMaxDsDeclarativeThought(int[] nums) {
        IntSummaryStatistics statistics = Arrays.stream(nums).summaryStatistics();

        int MIN_VALUE = statistics.getMin();
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = statistics.getMax();
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

    // Sort Example
    public static void minAndMaxDsSort(int[] nums) {
        Arrays.sort(nums);

        int MIN_VALUE = nums[0];
        System.out.println("MIN_VALUE = " + MIN_VALUE);

        int MAX_VALUE = nums[nums.length - 1];
        System.out.println("MAX_VALUE = " + MAX_VALUE);
    }

}
