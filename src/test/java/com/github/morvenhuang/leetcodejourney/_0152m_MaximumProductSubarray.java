package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * <p>
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class _0152m_MaximumProductSubarray {

    @Test
    void test01() {
        int[] a = {2, 3, -2, 4, 1, -1, -100};
        int r = slt(a);
        Assertions.assertEquals(400, r);
    }

    @Test
    void test02() {
        int[] a = {2, 3, -2, 4};
        int r = slt(a);
        Assertions.assertEquals(6, r);
    }

    @Test
    void test03() {
        int[] a = {-2, 0, -1};
        int r = slt(a);
        Assertions.assertEquals(0, r);
    }

    /**
     * 思路，这一题对0要进行特殊处理，更重要的，对负数要进行特殊处理。
     * 一遍循环，当到i的时候，表示的是以a[i]结尾的子串的最大值（最大正值、最大负值都要记录），
     * 核心点有二：
     * 1. 循环到i时，表是的是所有以a[i]为结尾的所有可能子串，而不是a[0]~a[i]的所有可能子串。只有这种设定，才能从i推导到i+1。
     * 2. 记录以a[i]为结尾的所有子串的最大值时，最大正值、最大负值都要记录。
     */
    int slt(int[] a) {
        int product = 0;
        Integer positive = null;
        Integer negative = null;

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                if (product < 0) {
                    product = 0;
                }
                positive = null;
                negative = null;
                continue;
            } else if (a[i] > 0) {
                // 当前是一个正数，那么以 i-1 结尾的最大正数 positive，最大负数 negative，如何推导出以 i 结尾的最大正数、最大负数？
                if (positive == null && negative == null) {
                    positive = a[i];
                } else if (positive == null) {
                    // 该分支表示以a[i-1]为结尾的子串中，没有任何正数结果产生，只能产生负数结果。
                    // 现在a[i]又是个正数，那么以a[i]结尾的子串，要么和a[i-1]相连，这时候，只能产生负数结果；要么a[i]独立作为子串，那么只会产生正数结果，即a[i].
                    negative = negative * a[i];
                    positive = a[i];
                } else if (negative == null) {
                    positive = positive * a[i];
                } else {
                    negative = negative * a[i];
                    positive = positive * a[i];
                }
            } else {
                if (positive == null && negative == null) {
                    negative = a[i];
                } else if (positive == null) {
                    positive = negative * a[i];
                    negative = a[i];
                } else if (negative == null) {
                    negative = positive * a[i];
                    positive = null;
                } else {
                    int temp = positive; // 注意
                    positive = negative * a[i];
                    negative = temp * a[i];
                }
            }
            if (positive != null && product < positive) {
                product = positive;
            }
        }

        return product;
    }
}
