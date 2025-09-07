package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that
 * a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class _0018m_FourSum {

    @Test
    public void test01() {
        int[] a = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> ret = slt2(a, 0);
        assertEquals(3, ret.size());
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-1, 0, 0, 1));
        expected.add(Arrays.asList(-2, -1, 1, 2));
        expected.add(Arrays.asList(-2, 0, 0, 2));
        assertTrue(TestHelper.same2DLists(expected, ret, true, true));
    }

    @Test
    public void testNoSolutionCase() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 100;
        List<List<Integer>> result = slt2(nums, target);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMultipleSolutionsWithDuplicates() {
        int[] nums = {2, 2, 2, 2, 2, 2, 2, 2};
        List<List<Integer>> ret = slt2(nums, 8);
        assertEquals(1, ret.size());
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 2, 2, 2));
        assertTrue(TestHelper.same2DLists(expected, ret, true, true));
    }

    @Test
    public void testNegativeNumbers() {
        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};

        List<List<Integer>> ret = slt2(nums, 0);
        assertEquals(8, ret.size());

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(-3, -2, 2, 3));
        expected.add(Arrays.asList(-3, -1, 1, 3));
        expected.add(Arrays.asList(-3, 0, 0, 3));
        expected.add(Arrays.asList(-3, 0, 1, 2));
        expected.add(Arrays.asList(-2, -1, 0, 3));
        expected.add(Arrays.asList(-2, -1, 1, 2));
        expected.add(Arrays.asList(-2, 0, 0, 2));
        expected.add(Arrays.asList(-1, 0, 0, 1));
        assertTrue(TestHelper.same2DLists(expected, ret, true, true));
    }

    //

    /**
     * 首先想到的就是和 three-sum 一样的解法，排序，以及最后一层用 hashset
     */
    List<List<Integer>> slt(int[] a, int target) {
        Arrays.sort(a);

        List<List<Integer>> ret = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        for (int value : a) {
            set.add(value);
        }

        for (int i = 0; i <= a.length - 4; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }

            for (int j = i + 1; j <= a.length - 3; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) {
                    continue;
                }

                for (int k = j + 1; k <= a.length - 2; k++) {
                    if (k > j + 1 && a[k] == a[k - 1]) {
                        continue;
                    }
                    int remaining = target - a[i] - a[j] - a[k];
                    if (remaining > a[k]) {
                        if (set.contains(remaining)) {
                            ret.add(Arrays.asList(a[i], a[j], a[k], remaining));
                        }
                    } else if (remaining == a[k]) {
                        if (k < a.length - 1 && a[k + 1] == remaining) {
                            ret.add(Arrays.asList(a[i], a[j], a[k], remaining));
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * 与 three sum 类似，除了用 hashset 来降低时间复杂度，对于排序数组，我们一样可以用"头尾双指针"。
     */
    List<List<Integer>> slt2(int[] a, int target) {
        Arrays.sort(a);

        List<List<Integer>> ret = new ArrayList<>();

        for (int i = 0; i <= a.length - 4; i++) {
            if (i > 0 && a[i] == a[i - 1]) {
                continue;
            }

            for (int j = i + 1; j <= a.length - 3; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) {
                    continue;
                }

                int remaining = target - a[i] - a[j];

                // 双指针
                int head = j + 1;
                int tail = a.length - 1;
                while (head < tail) {
                    if (head > j + 1 && a[head] == a[head - 1]) { // 跳过重复
                        head++;
                        continue;
                    }
                    if (tail < a.length - 1 && a[tail] == a[tail + 1]) { // 跳过重复
                        tail--;
                        continue;
                    }
                    if (a[head] + a[tail] == remaining) {
                        ret.add(Arrays.asList(a[i], a[j], a[head], a[tail]));
                        head++;
                        tail--;
                    } else if (a[head] + a[tail] > remaining) {
                        tail--;
                    } else {
                        head++;
                    }
                }
            }
        }
        return ret;
    }
}
