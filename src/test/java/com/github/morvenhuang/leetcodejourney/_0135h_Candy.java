package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * <p>
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * <p>
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * <p>
 * Constraints:
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 */
public class _0135h_Candy {

    @Test
    void test01() {
        int[] a = {1, 0, 2};
        int r = slt(a);
        Assertions.assertEquals(5, r);
    }

    @Test
    void test02() {
        int[] a = {1, 2, 2};
        int r = slt(a);
        Assertions.assertEquals(4, r);
    }

    @Test
    void test03() {
        int[] a = {1, 2, 3, 4, 5};
        int r = slt(a);
        Assertions.assertEquals(15, r);
    }

    @Test
    void test04() {
        int[] a = {5, 4, 3, 2, 1};
        int r = slt(a);
        Assertions.assertEquals(15, r);
    }

    @Test
    void test05() {
        int[] a = {1, 2, 3, 4, 2, 1};
        int r = slt(a);
        Assertions.assertEquals(13, r);
    }

    @Test
    void test06() {
        int[] a = {3, 3, 3, 3};
        int r = slt(a);
        Assertions.assertEquals(4, r);
    }

    @Test
    void test07() {
        int[] a = {1, 1, 2, 3, 5, 5, 4, 4, 4, 3, 2, 1, 1};
        //        {1, 1, 2, 3, 4, 2, 1, 1, 4, 3, 2, 1, 1}
        int r = slt(a);
        Assertions.assertEquals(26, r);
    }

    /**
     * 其实这道题的思路不算 hard，比较容易能想到，波谷位置肯定要填1（就是给一个糖果）。
     * 然后从波谷位置向两侧爬，每次递增1是最优的。
     * 最直观的解法，就是找到所有波谷，然后对每个波谷向两侧攀爬，但试了一下，写出来比较麻烦，各种 if 判断，尤其是题目还允许重复数值。
     * 后来想，比较土一点，第一遍，从数组头到数组尾，只处理递增的部分，就是爬坡。
     * 在从数组尾到数组头，也是处理递增的部分（这实际是数组中的递减部分）。
     * 然后处理一个特殊情况，就是在峰顶，可能出现冲突，比如第一遍爬到峰顶是4，第二遍爬到同一峰顶是5，那显然要用5。
     * 还有就是相邻如果有重复数值，这个就是下面各个 if 中是使用 > 还是 >=，是使用 < 还是 <=，要注意想一想。
     *
     * 这3个 for 循环，可能可以合并优化，但先保留这样，便于理解。
     */
    int slt(int[] a) {
        int[] candies = new int[a.length];

        // 处理升序部分
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                if (a[i + 1] >= a[i]) {
                    candies[i] = 1;
                }
            } else if (i == a.length - 1) {
                if (a[i - 1] >= a[i]) {
                    candies[i] = 1;
                } else {
                    candies[i] = candies[i - 1] + 1;
                }
            } else {
                if (a[i - 1] >= a[i] && a[i] <= a[i + 1]) { // 波谷
                    candies[i] = 1;
                } else if (a[i] > a[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                }
            }
        }

        // 处理降序部分（对于这里的逆序 for 循环，相当于处理升序）
        for (int i = a.length - 1; i > -1; i--) {
            if (i == a.length - 1) {
                if (a[i - 1] >= a[i]) {
                    candies[i] = 1;
                }
            } else if (i == 0) {
                if (a[i + 1] >= a[i]) {
                    candies[i] = 1;
                } else {
                    candies[i] = Math.max(candies[i], candies[i + 1] + 1);
                }
            } else {
                if (a[i - 1] >= a[i] && a[i] <= a[i + 1]) { // 波谷
                    candies[i] = 1;
                } else if (a[i] > a[i + 1]) {
                    candies[i] = Math.max(candies[i], candies[i + 1] + 1);
                }
            }
        }

        int sum = 0;
        for (int c : candies) {
            sum += c;
        }
        return sum;
    }
}
