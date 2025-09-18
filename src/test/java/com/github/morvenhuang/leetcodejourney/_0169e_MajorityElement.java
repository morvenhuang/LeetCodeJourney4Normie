package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * <p>
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * <p>
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * <p>
 * Constraints:
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class _0169e_MajorityElement {

    @Test
    void test01() {
        int[] a = {3, 2, 3};
        int r = slt(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    void test02() {
        int[] a = {2, 2, 1, 1, 1, 2, 2};
        int r = slt(a);
        Assertions.assertEquals(2, r);
    }

    // linear time 很好解决，O(1) space 要想一想。
    // 没思路。
    // 看网上解法，这个实际上是一个知名算法：摩尔投票法(Boyer-Moore Voting Algorithm)，是一种高效的算法,主要用于在数组中寻找出现次数超过一半的元素,即多数元素。
    // 这个算法的逻辑是：一个候选者，和它的投票数，遇到和当前候选者相同的，投票数就+1，不同就-1，如果减没了，就换当前元素作为候选者。
    // 由于肯定存在一个数，它的出现次数超过半数，那么，最终被留下的肯定是它，因为它不会被减到0
    int slt(int[] a) {
        int candidate = a[0];
        int count = 1;

        for (int i = 1; i < a.length; i++) {
            if (count == 0) {
                candidate = a[i];
                count = 1;
            } else if (a[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
