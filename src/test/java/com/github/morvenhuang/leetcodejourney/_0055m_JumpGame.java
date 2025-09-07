package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * <p>
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 * jump length is 0, which makes it impossible to reach the last index.
 */
public class _0055m_JumpGame {

    @Test
    public void test01() {
        int[] a = {2, 3, 1, 1, 4};
        Assertions.assertTrue(slt(a));
    }

    @Test
    public void test02() {
        int[] a = {3, 2, 1, 0, 4};
        Assertions.assertTrue(!slt(a));
    }

    // jump game II 用的是什么方法？贪心算法
    // 这里应该不能用贪心算法？
    // DFS?
    boolean slt(int[] a) {
        return process(a, 0);
    }

    boolean process(int[] a, int i) {
        if (a[i] == 0) {
            return false;
        }
        if (i + a[i] >= a.length - 1) {
            return true;
        }
        for (int m = 1; m <= a[i]; m++) {
            boolean r = process(a, i + m);
            if (r) {
                return true;
            }
        }
        return false;
    }

    // 别人的解法
    // 每走一步，记录一下当前可达的最远距离，当走到第i个元素时，此前最远可达如果小于i，则说明i不可达
    boolean slt2(int[] a) {
        if (a.length == 1) {
            return true;
        }

        int furthest = 0;
        for (int i = 0; i < a.length; i++) {
            if (furthest < i) {
                return false;
            }
            furthest = Math.max(furthest, i + a[i]);
        }
        return true;
    }
}
