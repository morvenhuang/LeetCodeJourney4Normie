package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to
 * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * Example:
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class _0016m_ThreeSumClosest {

    @Test
    public void testBasicClosestSum() {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int result = slt(nums, target);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testExactMatch() {
        int[] nums = {1, 2, 3, 4};
        int target = 6;
        int result = slt(nums, target);
        Assertions.assertEquals(6, result);
    }

    @Test
    public void testNegativeTarget() {
        int[] nums = {0, -1, 5, 9, -3};
        int target = -2;
        int result = slt(nums, target);
        Assertions.assertEquals(-4, result);
    }

    @Test
    public void testLargeNumbers() {
        int[] nums = {1000, -2000, 1500, -500, 3000};
        int target = 2500;
        int result = slt(nums, target);
        Assertions.assertEquals(2500, result);
    }

    @Test
    public void testDuplicatesAndZero() {
        int[] nums = {1, 1, 1, 0, 0};
        int target = 100;
        int result = slt(nums, target);
        Assertions.assertEquals(3, result);
    }

    /**
     * 题目的意思是要找到最接近给定值的3数只和，属于 three-sum 的升级版。
     * 但是不能用 hashset/hashmap 了，因为最后不是找具体一个值，而是找最接近的。
     * 那么，还有什么常见的减少循环嵌套层数的方法呢？
     * 那就是：排序 + 头尾双指针。
     */
    public int slt(int[] a, int target) {
        Arrays.sort(a);

        int diff = Integer.MAX_VALUE;
        for (int i = 0; i <= a.length - 3; i++) {
            int left = i + 1;
            int right = a.length - 1;
            int twoSum = target - a[i];
            while (left < right) {
                int s = a[left] + a[right];
                if (s == twoSum) {
                    return target;
                } else if (s > twoSum) {
                    if (s - twoSum < Math.abs(diff)) {
                        diff = s - twoSum;
                    }
                    right--;
                } else {
                    if (twoSum - s < Math.abs(diff)) {
                        diff = s - twoSum;
                    }
                    left++;
                }
            }
        }

        return target + diff;
    }
}
