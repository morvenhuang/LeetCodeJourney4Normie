package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1]
 */
public class _0001e_TwoSum {

    @Test
    void testBasicCase() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = slt3(nums, target);
        Assertions.assertTrue(TestHelper.sameElements(new int[]{0, 1}, result));
    }

    @Test
    void testNegativeNumbers() {
        int[] nums = {-3, 4, 3, 90};
        int target = 0;
        int[] result = slt3(nums, target);
        Assertions.assertTrue(TestHelper.sameElements(new int[]{0, 2}, result));
    }

    @Test
    void testDuplicateNumbers() {
        int[] nums = {3, 3};
        int target = 6;
        int[] result = slt3(nums, target);
        Assertions.assertTrue(TestHelper.sameElements(new int[]{0, 1}, result));
    }

    @Test
    void testNoSolution() {
        int[] nums = {1, 2, 3, 4};
        int target = 10;
        int[] result = slt3(nums, target);
        Assertions.assertNull(result);
    }

    @Test
    void testZeroTarget() {
        int[] nums = {11, 0, 4, 3, 0};
        int target = 0;
        int[] result = slt3(nums, target);
        Assertions.assertTrue(TestHelper.sameElements(new int[]{1, 4}, result));
    }

    /**
     * 这是 LeetCode 开篇第一题，题目理解起来应该没有难度。解法的话，最简单的就是直接两层 for 循环，这种写法的时间复杂度是 O(n^2)。
     * 备注：以理解思路为主，这里未做数组长度这些简单的边界条件的检查，下同。
     */
    int[] slt(int[] a, int target) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 1; j < a.length; j++) {
                if (a[i] + a[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 想要降低解法的时间复杂度，这里出现一个很常见的方法，就是使用 hashset/hashmap 来减少一次嵌套循环。
     * 备注：记住 hashset/hashmap 的这种用法。
     */
    int[] slt2(int[] a, int target) {
        // 将所有值，及其对应的索引放入 hashmap。从 hashmap 中查找一个值的时间复杂度是 O(1)，因此可以减少时间复杂度。
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < a.length; i++) {
            map.put(a[i], i);
        }
        for (int i = 0; i < a.length - 1; i++) {
            int remaining = target - a[i];
            if (map.containsKey(remaining)) {
                int j = map.get(remaining);
                if (j != i) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 对上述方法进行微调，不预先将整个数组放入 hashmap，而是边判断边放入，代码简化，还能避免在 hashmap 中取到自身。
     */
    int[] slt3(int[] a, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int remaining = target - a[i];
            if (map.containsKey(remaining)) {
                int j = map.get(remaining);
                return new int[]{i, j};
            } else {
                map.put(a[i], i);
            }
        }
        return null;
    }
}
