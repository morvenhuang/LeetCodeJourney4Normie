package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 * <p>
 * <p>
 * Follow up:
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 */
public class _0189m_RotateArray {

    @Test
    void test01() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        slt(a, 3);
        int[] expected = {5, 6, 7, 1, 2, 3, 4};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, a, false));
    }

    /**
     * 链表最简单，但是不符合 O(1) 空间复杂度。
     * 不用链表，那就是在数组内部慢慢挪。
     */
    void slt(int[] a, int k) {
        for (int i = 0; i < k; i++) {
            int tailVal = a[a.length - 1];
            for (int j = a.length - 2; j >= 0; j--) {
                a[j + 1] = a[j];
            }
            a[0] = tailVal;
        }
    }

    // 经典的3此翻转，时间复杂度比 slt 好，
    void slt2(int[] a, int k) {
        reverse(a, 0, a.length - 1);
        reverse(a, 0, k - 1);
        reverse(a, k, a.length - 1);
    }

    void reverse(int[] a, int start, int end) {

    }
}
