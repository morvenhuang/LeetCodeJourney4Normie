package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique
 * combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class _0039m_CombinationSum {

    @Test
    public void test01() {
        int[] a = {2, 3, 6, 7};
        List<List<Integer>> r = slt(a, 7);
        Assertions.assertEquals(2, r.size());
        Assertions.assertTrue(TestHelper.containsList(r, Arrays.asList(7)));
        Assertions.assertTrue(TestHelper.containsList(r, Arrays.asList(2, 2, 3)));
    }

    @Test
    public void test02() {
        int[] a = {2, 3, 5};
        List<List<Integer>> r = slt(a, 8);
        Assertions.assertEquals(3, r.size());
        Assertions.assertTrue(TestHelper.containsList(r, Arrays.asList(2, 2, 2, 2)));
        Assertions.assertTrue(TestHelper.containsList(r, Arrays.asList(2, 3, 3)));
        Assertions.assertTrue(TestHelper.containsList(r, Arrays.asList(3, 5)));
    }

    @Test
    public void test03() {
        int[] a = {2, 4, 6};
        List<List<Integer>> r = slt(a, 7);
        Assertions.assertEquals(0, r.size());
    }

    /**
     * 第一想法是递归？但是本题没有限制数组的大小，递归可能溢出。
     */
    List<List<Integer>> slt(int[] a, int target) {
        Arrays.sort(a);
        return process(a, 0, target);
    }

    // 最开始觉得可以不用 index 这个参数，但发现结果会有重复组合（顺序不同），想一下，如果每一次递归内也允许查找整个数组，肯定会出现这种情况
    List<List<Integer>> process(int[] a, int index, int target) {
        List<List<Integer>> list = new ArrayList<>();
        // 由于这里有 for 循环，我们不需要/不能在 for 里 return，否则会导致找到一个就停止了
        for (int i = index; i < a.length; i++) {
            if (a[i] > target) {
                break;
            } else if (a[i] == target) {
                List<Integer> l = new ArrayList<>(Arrays.asList(a[i]));
                list.add(l);
            } else {
                // 对于嵌套，不允许其查找当前位置 i 之前的元素，但可以查找当期值（题目中允许重复使用）。
                List<List<Integer>> sub = process(a, i, target - a[i]);
                if (!sub.isEmpty()) {
                    List<Integer> curr = new ArrayList<>(Arrays.asList(a[i]));
                    for (List<Integer> x : sub) {
                        List<Integer> join = new ArrayList<>();
                        join.addAll(curr);
                        join.addAll(x);
                        list.add(join);
                    }
                }
            }
        }
        return list;
    }
}
