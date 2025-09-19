package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 * <p>
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class _0198m_HouseRobber {

    @Test
    void test01() {
        int[] a = {1, 2, 3, 1};
        int r = slt(a);
        Assertions.assertEquals(4, r);
    }

    @Test
    void test02() {
        int[] a = {2,7,9,3,1};
        int r = slt(a);
        Assertions.assertEquals(12, r);
    }

    // 这我记得是 dp？
    int slt(int[] a) {
        int len = a.length;
        int ignoreCurrElement = 0;
        int useCurrElement = a[0];

        for (int i = 1; i < len; i++) {
            int curr1 = a[i] + ignoreCurrElement;
            int curr0 = useCurrElement;
            ignoreCurrElement = curr0;
            useCurrElement = curr1;
        }
        return Math.max(ignoreCurrElement, useCurrElement);
    }
}
