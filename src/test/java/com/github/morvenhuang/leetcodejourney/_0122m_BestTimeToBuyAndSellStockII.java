package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times).
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 * <p>
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * <p>
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 * <p>
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class _0122m_BestTimeToBuyAndSellStockII {

    @Test
    void test01() {
        int[] a = {7, 1, 5, 3, 6, 4};
        int r = slt2(a);
        Assertions.assertEquals(7, r);
    }

    @Test
    void test02() {
        int[] a = {1, 2, 3, 4, 5};
        int r = slt2(a);
        Assertions.assertEquals(4, r);
    }

    @Test
    void test03() {
        int[] a = {7, 6, 4, 3, 1};
        int r = slt2(a);
        Assertions.assertEquals(0, r);
    }

    // 最优应该是 每一个波谷到波峰的获利，再加起来？猜测是这样。
    int slt(int[] a) {
        int min = a[0];
        int sum = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1] && (i == a.length - 1 || a[i] > a[i + 1])) {  // a[i] 为波峰
                sum += a[i] - min; // 波峰减去它前面的波谷
                min = a[i];
                continue;
            }
            min = Math.min(min, a[i]);
        }
        return sum;
    }

    // 还有一种理解，就是我把每一个上升区间的获利都占有了，就能最大化利益，即如果明天是涨价的，那就今天买，明天卖。
    int slt2(int[] a) {
        int sum = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                sum += a[i] - a[i - 1];
            }
        }
        return sum;
    }
}
