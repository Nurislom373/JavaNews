package org.khasanof.greedy.coin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Nurislom
 * @see org.khasanof.greedy
 * @since 10/14/2024 11:23 PM
 */
public class CoinChange {

    public static void main(String[] args) {
        ArrayList<Integer> integers = coinChangeProblem(73);
        System.out.println("integers = " + integers);
    }

    // Function to solve the coin change problem
    public static ArrayList<Integer> coinChangeProblem(int amount) {
        // Define an array of coin denominations in descending order
        Integer[] coins = {1, 2, 5, 10, 20, 50, 100, 500, 2000};

        // Sort the coin denominations in descending order
        Arrays.sort(coins, Comparator.reverseOrder());

        ArrayList<Integer> ans = new ArrayList<>(); // List to store selected coins

        // Iterate through the coin denominations
        for (Integer coin : coins) {
            // Check if the current coin denomination can be used to reduce the remaining amount
            if (coin <= amount) {
                // Repeatedly subtract the coin denomination from the remaining amount
                while (coin <= amount) {
                    ans.add(coin); // Add the coin to the list of selected coins
                    amount -= coin; // Update the remaining amount
                }
            }
        }
        return ans;
    }
}
