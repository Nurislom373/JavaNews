package org.khasanof;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/21/2025 3:50 PM
 */
public class TargetSumCalculator {

    public static void main(String[] args) {
        double[] numbers = {215.41, 405.08, 151.44, 147.22, 937.13, 239.91, 491.45, 521.17, 768.99, 613.84};
        double target = 143.5;

        List<String> expressions = new ArrayList<>();
        if (findExpression(numbers, 0, 0, "", target, expressions)) {
            System.out.println("Expression found: " + expressions.get(0));
        } else {
            System.out.println("No valid expression found.");
        }
    }

    private static boolean findExpression(double[] numbers, int index, double currentSum, String expression, double target, List<String> result) {
        if (index == numbers.length) {
            if (Math.abs(currentSum - target) < 0.01) {
                result.add(expression);
                return true;
            }
            return false;
        }

        // Try adding the current number
        if (findExpression(numbers, index + 1, currentSum + numbers[index],
                expression + (expression.isEmpty() ? "" : " + ") + numbers[index], target, result)) {
            return true;
        }

        // Try subtracting the current number
        if (findExpression(numbers, index + 1, currentSum - numbers[index],
                expression + (expression.isEmpty() ? "" : " - ") + numbers[index], target, result)) {
            return true;
        }

        // Skip the current number
        return findExpression(numbers, index + 1, currentSum, expression, target, result);
    }
}
