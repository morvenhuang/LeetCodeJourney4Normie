package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i. <br>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), <br>
 * design an algorithm to find the maximum profit. <br>
 * Note that you cannot sell a stock before you buy one. <br>
 * <br>
 * Example 1: <br>
 * Input: [7,1,5,3,6,4] <br>
 * Output: 5 <br>
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5. <br>
 * Not 7-1 = 6, as selling price needs to be larger than buying price. <br>
 * <br>
 * Example 2: <br>
 * Input: [7,6,4,3,1] <br>
 * Output: 0 <br>
 * Explanation: In this case, no transaction is done, i.e. max profit = 0. <br>
 */
public class _0121e_BestTimeToBuyAndSellStock {

    @Test
    void test01() {
        int[] a = {7, 1, 5, 3, 6, 4};
        int r = slt(a);
        Assertions.assertEquals(5, r);
    }

    // 当前值 减去 此前的最小值，就是当前日期卖出可获得的最大利润
    int slt(int[] a) {
        int maxProfit = 0;
        int minPrice = a[0];

        for (int i = 1; i < a.length; i++) {
            int profit = a[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);
            minPrice = Math.min(minPrice, a[i]);
        }
        return maxProfit;
    }
}
