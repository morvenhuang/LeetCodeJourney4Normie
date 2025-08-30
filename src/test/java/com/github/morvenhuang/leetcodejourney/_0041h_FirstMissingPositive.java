package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Example 1:
 * Input: [1,2,0]
 * Output: 3
 * <p>
 * Example 2:
 * Input: [3,4,-1,1]
 * Output: 2
 * <p>
 * Example 3:
 * Input: [7,8,9,11,12]
 * Output: 1
 * <p>
 * Note:
 * Your algorithm should run in O(n) time and uses constant extra space.
 */
public class _0041h_FirstMissingPositive {

    @Test
    public void test01() {
        int[] a = {1, 2, 0};
        int r = slt(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    public void test02() {
        int[] a = {3, 4, -1, 1};
        int r = slt(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test03() {
        int[] a = {7, 8, 9, 11, 12};
        int r = slt(a);
        Assertions.assertEquals(1, r);
    }

    /**
     * 排序是做不到 O(n) 的，所以本题的难点就在这个时间复杂度上。
     * 本题做完后会发现，有时候你第一时间想到的那个看似"暴力"的解法，其实并不低效。
     */
    int slt(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int i : a) {
            if (i > 0) {
                set.add(i);
            }
        }

        int v = 1;
        // 现在的问题：为什么这里是 O(n) 时间复杂度？
        // 我们从1开始递增，最差的情况是，从1开始的数在数组 a 中都存在，这种情况，也就是遍历 a.length 次。
        while (true) {
            if (!set.contains(v)) {
                return v;
            }
            v++;
        }
    }
}
