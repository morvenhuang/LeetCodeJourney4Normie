package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * The solution set must not contain duplicate triplets.
 * Example:
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class _0015m_ThreeSum {

    @Test
    public void testStandardCase() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = slt2(nums);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(TestHelper.containsList(result, Arrays.asList(-1, -1, 2)));
        Assertions.assertTrue(TestHelper.containsList(result, Arrays.asList(-1, 0, 1)));
    }

    @Test
    public void testAllZeros() {
        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> result = slt2(nums);
        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(TestHelper.containsList(result, Arrays.asList(0, 0, 0)));
    }

    @Test
    public void testNoSolution() {
        int[] nums = {1, 2, -2, -1};
        List<List<Integer>> result = slt2(nums);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testDuplicateTriplets() {
        int[] nums = {-2, 0, 1, 1, 2, 2};
        List<List<Integer>> result = slt2(nums);
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(TestHelper.containsList(result, Arrays.asList(-2, 0, 2)));
        Assertions.assertTrue(TestHelper.containsList(result, Arrays.asList(-2, 1, 1)));
    }

    /**
     * 最直观的做法是3重循环，不过这种情况时间复杂度太高，O(n^3)，需要考虑优化。
     * 可以从 two sum 中得到启发，如果固定一个值，剩下的就可以套用 two sum 的解法了。
     *
     * 其实本题中，真正比较麻烦的是如何避免重复答案。
     * 例如，以下代码就会输出重复答案：
     * [-1, 0, 1], [-1, 2, -1], [0, 1, -1]
     */
    List<int[]> slt(int[] a) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i <= a.length - 3; i++) {
            int target = -a[i];

            Map<Integer, Integer> map = new HashMap<>(); // 这里其实不需要 hashmap，用 hashset 就行了，但这不是重点
            for (int j = i + 1; j <= a.length - 1; j++) {
                if (map.containsKey(target - a[j])) {
                    list.add(new int[]{a[i], a[j], target - a[j]});
                } else {
                    map.put(a[j], j);
                }
            }
        }
        return list;
    }

    /**
     * 这里的实现基本上在内层循环套用 two-sum 的做法，但对重复数据进行特殊处理
     * 更好的做法是在内层循环使用双指针从两头相向移动，不过我觉得差别不大。
     */
    List<List<Integer>> slt2(int[] a) {
        Arrays.sort(a); // 对原数组进行排序，这样对重复数据处理起来会比较简化

        Set<Integer> set = new HashSet<>();
        for (int j = 0; j <= a.length - 1; j++) {
            set.add(a[j]);
        }

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= a.length - 3; i++) {
            // 如果当前元素，与前一元素相同，则跳过
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }
            int target = -a[i];

            // 以下基本上是 two-sum 的逻辑，有两点区别
            // 1. 不需要在最终结果返回索引，这里也就不要使用 hashmap
            // 2. 如何避免重复的结果

            for (int j = i + 1; j < a.length - 1; j++) {
                // 类似上面的逻辑，在 two-sum 中，跳过重复元素
                if (j > i + 1 && a[j] == a[j - 1]) {
                    continue;
                }
                int need = target - a[j];
                // 如果需要的值比当前值还小，由于现在的数组 a 已经是排序后的，后续的元素中不可能存在这样的值，直接退出循环。
                if (need < a[j]) {
                    break;
                } else if (need == a[j]) {
                    // 如果需求值与当前值相同，则只需要判断当前值的后一元素（记住 a 已经是排序后的了）。
                    // 这里为什么要提前处理 j+1 元素？如果不处理，本层循环下一次就有可能会跳过 j+1 了，注意本层循环也有一个逃过重复元素的动作
                    if (need == a[j + 1]) {
                        list.add(Arrays.asList(a[i], a[j], need));
                    } else {
                        break;
                    }
                } else { // 只有当需求值大于当前值，才需要到 hashset 中判断有无。
                    if (set.contains(need)) {
                        list.add(Arrays.asList(a[i], a[j], need));
                    }
                }
            }
        }
        return list;
    }
}
