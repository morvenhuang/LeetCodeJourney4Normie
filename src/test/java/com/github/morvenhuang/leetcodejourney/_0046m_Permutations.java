package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class _0046m_Permutations {

    @Test
    public void test01() {
        int[] a = {1, 2, 3};
        List<List<Integer>> ret = slt2(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2, 3));
        expected.add(Arrays.asList(1, 3, 2));
        expected.add(Arrays.asList(2, 1, 3));
        expected.add(Arrays.asList(2, 3, 1));
        expected.add(Arrays.asList(3, 1, 2));
        expected.add(Arrays.asList(3, 2, 1));
        Assertions.assertTrue(TestHelper.same2DLists(expected, ret, true, false));
    }

    /**
     * 纯递归。
     * 递归是最简单的想法，但在数组稍长时，会栈溢出，另外效率也比较低。
     */
    List<List<Integer>> slt(int[] a) {
        return process(a);
    }

    List<List<Integer>> process(int[] remaining) {
        List<List<Integer>> ret = new ArrayList<>();
        if (remaining.length == 2) {
            ret.add(Arrays.asList(remaining[0], remaining[1]));
            ret.add(Arrays.asList(remaining[1], remaining[0]));
            return ret;
        }
        if (remaining.length == 1) {
            ret.add(Arrays.asList(remaining[0]));
            return ret;
        }
        for (int i = 0; i < remaining.length; i++) {
            int[] newRemaining = removeElement(remaining, i);
            List<List<Integer>> sub = process(newRemaining);
            for (List<Integer> x : sub) {
                List<Integer> list = new ArrayList<>();
                list.add(remaining[i]);
                list.addAll(x);
                ret.add(list);
            }
        }
        return ret;
    }

    /**
     * 回溯。
     */
    List<List<Integer>> slt2(int[] a) {
        List<Integer> currList = new ArrayList<>();
        List<List<Integer>> finalResult = new ArrayList<>();
        process2(a, currList, finalResult);
        return finalResult;
    }

    void process2(int[] a, List<Integer> currList, List<List<Integer>> finalResult) {
        if (currList.size() == a.length) {
            finalResult.add(new ArrayList<>(currList)); // 复制（重要），然后存入最终结果
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (currList.contains(a[i])) {
                continue;
            }
            currList.add(a[i]);
            process2(a, currList, finalResult);
            currList.remove(currList.size() - 1); // 回溯
        }
    }

    public static int[] removeElement(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + array.length);
        }

        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        return newArray;
    }


}
