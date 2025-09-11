package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class _0128m_LongestConsecutiveSequence {

    @Test
    void test01() {
        int[] a = {100, 4, 200, 1, 3, 2};
        int r = slt(a);
        Assertions.assertEquals(4, r);
    }

    @Test
    void test02() {
        int[] a = {100, 4, 200, 1, 3, 2, 10, 9, 7, 8, 11};
        int r = slt(a);
        Assertions.assertEquals(5, r);
    }

    @Test
    void test03() {
        int[] a = {3, 2, 1, 4, 5};
        int r = slt(a);
        Assertions.assertEquals(5, r);
    }

    /**
     * 这个有非常 tricky 的解法吗？
     * 这里是正常的想法，就是遍历数组，拿到一个数就往两端发展，如果两端都没有，也就意味这当前数所在连续序列已经结束。
     * 这里需要想清楚一个点：就是再向两端扩展过程中，使用过的数，那么后续其实就不需要再处理了。
     */
    int slt(int[] a) {
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            values.add(a[i]);
        }

        Set<Integer> usedValues = new HashSet<>();
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (usedValues.contains(a[i])) {
                continue;
            }
            int curr = a[i];
            usedValues.add(curr);
            int len = 1;
            int l = curr - 1;
            int r = curr + 1;
            while (values.contains(l) || values.contains(r)) {
                if (values.contains(l)) {
                    usedValues.add(l);
                    l--;
                    len++;
                }
                if (values.contains(r)) {
                    usedValues.add(r);
                    r++;
                    len++;
                }
            }
            max = Math.max(max, len);
        }
        return max;
    }
}
