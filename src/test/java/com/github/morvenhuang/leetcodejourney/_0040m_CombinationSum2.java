package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
 * candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class _0040m_CombinationSum2 {

    @Test
    public void test01() {
        int[] a = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> r = slt(a, 8);
        Assertions.assertEquals(4, r.size());

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 7));
        expected.add(Arrays.asList(1, 2, 5));
        expected.add(Arrays.asList(2, 6));
        expected.add(Arrays.asList(1, 1, 6));

        Assertions.assertTrue(TestHelper.same2DLists(expected, r, true, true));
    }

    @Test
    public void test02() {
        int[] a = {2, 5, 2, 1, 2};
        List<List<Integer>> r = slt(a, 5);
        Assertions.assertEquals(2, r.size());

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2, 2));
        expected.add(Arrays.asList(5));
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, true, true));
    }

    List<List<Integer>> slt(int[] a, int target) {
        Arrays.sort(a);
        return process(a, 0, target);
    }

    List<List<Integer>> process(int[] a, int index, int target) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = index; i < a.length; i++) {
            // 这里与 #39 题不同
            // 注意这里是 i>index，不是 i>0，否则 test01 中的结果会少 {1,1,6}
            if (i > index && a[i] == a[i - 1]) {
                continue;
            }
            if (a[i] > target) {
                break;
            } else if (a[i] == target) {
                List<Integer> l = new ArrayList<>(Arrays.asList(a[i]));
                list.add(l);
            } else {
                // 这里的 index 与 #39 题不同
                List<List<Integer>> sub = process(a, i + 1, target - a[i]);
                if (!sub.isEmpty()) {
                    for (List<Integer> l : sub) {
                        List<Integer> nl = new ArrayList<>(Arrays.asList(a[i]));
                        nl.addAll(l);
                        list.add(nl);
                    }
                }
            }
        }
        return list;
    }
}
