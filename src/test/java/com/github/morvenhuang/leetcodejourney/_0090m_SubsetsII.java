package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set). <br>
 * Note: The solution set must not contain duplicate subsets. <br>
 * <p>
 * Example: <br>
 * Input: [1,2,2] <br>
 * Output: <br>
 * [ <br>
 * [2], <br>
 * [1], <br>
 * [1,2,2], <br>
 * [2,2], <br>
 * [1,2], <br>
 * [] <br>
 * ] <br>
 */
public class _0090m_SubsetsII {

    @Test
    public void test01() {
        int[] a = {1, 2, 2};
        List<List<Integer>> r = slt(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 2, 2));
        expected.add(Arrays.asList(2, 2));
        expected.add(Arrays.asList(1, 2));
        expected.add(Collections.emptyList());
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, true, true));
    }

    @Test
    public void test02() {
        int[] a = {1, 2, 2, 3};
        List<List<Integer>> r = slt(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(1, 2, 2));
        expected.add(Arrays.asList(1, 2, 2, 3));
        expected.add(Arrays.asList(1, 2, 3));
        expected.add(Arrays.asList(1, 3));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(2, 2));
        expected.add(Arrays.asList(2, 2, 3));
        expected.add(Arrays.asList(2, 3));
        expected.add(Arrays.asList(3));
        expected.add(Collections.emptyList());
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, true, true));
    }

    @Test
    public void test03() {
        int[] a = {1, 2, 2, 2};
        List<List<Integer>> r = slt(a);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(1, 2, 2));
        expected.add(Arrays.asList(1, 2, 2, 2));
        expected.add(Arrays.asList(2));
        expected.add(Arrays.asList(2, 2));
        expected.add(Arrays.asList(2, 2, 2));
        expected.add(Collections.emptyList());
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, true, true));
    }

    /**
     * 回顾 0078 求所有子集的方法，递归。这里的问题是，实际上原数组可以包含重复的数，而子集中也可以包含重复的数
     */
    List<List<Integer>> slt(int[] a) {
        List<List<Integer>> r = new ArrayList<>();
        process(a, 0, new ArrayList<>(), r);
        return r;
    }

    void process(int[] a, int i, List<Integer> list, List<List<Integer>> finalResult) {
        finalResult.add(new ArrayList<>(list)); // copy

        for (int m = i; m < a.length; m++) {
            // 这是与 0078 不同的地方。可以理解为在递归的同一层，循环的第二个元素开始，判断它是否与前一个元素相同，是的话则忽略。
            // 为什么要判断 m>i？因为如果从i开始判断，就涉及到上一层的递归了。
            // 另外，这里不要想的太深入，人的思维不适合想递归，这里只要想，在一个循环中，你往 list 加入一个元素，后来又删除该元素，
            // 如果下一个加入的元素是一样的值，那就不应该被加入，否则岂不是重复劳动。
            if (m > i && a[m] == a[m - 1]) {
                continue;
            }
            list.add(a[m]);
            process(a, m + 1, list, finalResult);
            list.remove(list.size() - 1);
        }
    }
}
