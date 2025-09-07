package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * Example:
 * Input: [1,1,2]
 * Output:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class _0047m_Permutations2 {

    @Test
    public void test01() {
        int[] a = {1, 1, 2};
        List<List<Integer>> ret = slt(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 1, 2));
        expected.add(Arrays.asList(1, 2, 1));
        expected.add(Arrays.asList(2, 1, 1));
        Assertions.assertTrue(TestHelper.same2DLists(expected, ret, true, false));
    }

    @Test
    public void test02() {
        int[] a = {1, 1};
        List<List<Integer>> ret = slt(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 1));
        Assertions.assertTrue(TestHelper.same2DLists(expected, ret, true, false));
    }

    @Test
    public void test03() {
        int[] a = {1, 1, 1, 2, 2};
        List<List<Integer>> ret = slt(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1,1,1,2,2));
        expected.add(Arrays.asList(1,1,2,1,2));
        expected.add(Arrays.asList(1,1,2,2,1));
        expected.add(Arrays.asList(1,2,1,1,2));
        expected.add(Arrays.asList(1,2,1,2,1));
        expected.add(Arrays.asList(1,2,2,1,1));
        expected.add(Arrays.asList(2,1,1,1,2));
        expected.add(Arrays.asList(2,1,1,2,1));
        expected.add(Arrays.asList(2,1,2,1,1));
        expected.add(Arrays.asList(2,2,1,1,1));
        Assertions.assertTrue(TestHelper.same2DLists(expected, ret, true, false));
    }

    /**
     * 根据 0046，使用回溯方法，这里的核心问题是，如何避免重复。
     */
    List<List<Integer>> slt(int[] a) {
        Arrays.sort(a);
        List<Integer> currList = new ArrayList<>();
        Set<Integer> currIndies = new HashSet<>();
        List<List<Integer>> finalResult = new ArrayList<>();
        process(a, currList, currIndies, finalResult);
        return finalResult;
    }

    void process(int[] a, List<Integer> currList, Set<Integer> currIndexList, List<List<Integer>> finalResult) {
        if (currList.size() == a.length) {
            finalResult.add(new ArrayList<>(currList));
            return;
        }
        int rm = -1;
        for (int i = 0; i < a.length; i++) {
            // 这里显然不能用 0046 中的 currList.contains(a[i]) 来判断
            if (currIndexList.contains(i)) {
                continue;
            }
            // 这里避免重复。想通了也很简单：就是回溯时抛掉的元素，就不要加入相同的值了。
            // 同时注意，这里处理重复元素，与 0015 双指针解法是不同的，那个解法不是递归。
            if (rm == a[i]) {
                continue;
            }
            currList.add(a[i]);
            currIndexList.add(i);
            process(a, currList, currIndexList, finalResult);
            currList.remove(currList.size() - 1);
            currIndexList.remove(i);
            rm = a[i];
        }
    }
}
