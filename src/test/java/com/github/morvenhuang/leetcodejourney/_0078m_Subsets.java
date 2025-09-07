package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class _0078m_Subsets {

    @Test
    public void test01() {
        int[] a = {1, 2, 3};
        List<List<Integer>> r = slt2(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(3));
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(1, 2, 3));
        expected.add(Arrays.asList(1, 3));
        expected.add(Arrays.asList(2, 3));
        expected.add(Arrays.asList(1, 2));
        expected.add(Collections.EMPTY_LIST);
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, true, true));
    }

    /**
     * 设集合 A 和 B 满足 A∩B=∅，即无交集，那么，二者的并集 A∪B 的所有子集，是 A 的所有子集与 B 的所有子集进行"叉乘"（笛卡尔积组合）。
     */
    List<List<Integer>> slt(int[] a) {
        return process(a, 0, a.length);
    }

    List<List<Integer>> process(int[] a, int from, int to) {
        List<List<Integer>> ret = new ArrayList<>();
        if (to - from == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(a[from]);
            ret.add(list);
            ret.add(Collections.EMPTY_LIST);
            return ret;
        }
        List<List<Integer>> sub1 = process(a, from, from + 1);
        List<List<Integer>> sub2 = process(a, from + 1, a.length);
        for (List<Integer> list1 : sub1) {
            for (List<Integer> list2 : sub2) {
                List<Integer> list = new ArrayList<>();
                list.addAll(list1);
                list.addAll(list2);
                ret.add(list);
            }
        }
        return ret;
    }

    // 回溯法，时间复杂度方面应该和上面的解法类似，但上面的解法会导致创建大量的 List，整体效率应该不如回溯法。
    // 代码也比较简洁，但不太好想。
    List<List<Integer>> slt2(int[] a) {
        List<List<Integer>> result = new ArrayList<>();
        process2(result, new ArrayList<>(), a, 0);
        return result;
    }

    void process2(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            process2(result, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
