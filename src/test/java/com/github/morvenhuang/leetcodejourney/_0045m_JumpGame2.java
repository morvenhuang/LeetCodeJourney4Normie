package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * （你当前所在元素，表示你可以跳跃的最大跨度，并不是你一定要跳这么大，你可以小于等于这个跨度）
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 * <p>
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 * <p>
 * Constraints:
 * 1 <= nums.length <= 1000 （意味着不能递归？）
 * 0 <= nums[i] <= 10^5
 */
public class _0045m_JumpGame2 {

    @Test
    public void test01() {
        int[] a = {2, 3, 1, 1, 4};
        int r = slt2(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test02() {
        int[] a = {2, 3, 0, 1, 4};
        int r = slt2(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test03() {
        int[] a = {2, 3, 3, 1, 4, 2};
        int r = slt2(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test04() {
        int[] a = {1, 1, 1, 1};
        int r = slt2(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    public void test05() {
        int[] a = {2, 2, 3, 5, 1, 1, 2};
        int r = slt2(a);
        Assertions.assertEquals(3, r);
    }

    // 递归，当数组长度过大的时候，不适用
    int slt(int[] a) {
        return process(a, 0);
    }

    int process(int[] a, int i) {
        if (a[i] == 0) {
            return -1;
        } else if (a[i] >= a.length - i - 1) {
            return 1;
        } else {
            int min = 1000;
            for (int j = 1; j <= a[i]; j++) {
                int r = process(a, i + j);
                if (r > 0 && r < min) {
                    min = r;
                }
            }
            return min + 1;
        }
    }

    int slt2(int[] a) {
        // init
        Stack<Tuple2> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) { // ignore zero
                continue;
            }
            if (i + a[i] >= a.length - 1) { // 如果步数足够多，直接返回1
                return 1;
            }
            for (int j = 1; j <= a[i]; j++) {
                stack.push(new Tuple2(i + j, 0, 1));
            }
            break;
        }

        int min = Integer.MAX_VALUE;
        while (!stack.empty()) {
            Tuple2 pop = stack.pop();
            if (pop.reach == 0) {
                continue;
            }
            if (pop.reach + a[pop.reach] >= a.length - 1) { // 下一步可达
                if (pop.steps + 1 < min) {
                    min = pop.steps + 1;
                }
            } else {
                for (int j = 1; j <= a[pop.reach]; j++) {
                    stack.push(new Tuple2(pop.reach + j, 0, pop.steps + 1));
                }
            }
        }
        return min;
    }

    static class Tuple2 {
        int reach;
        int jumpLen;
        int steps;

        public Tuple2(int ix, int valuex, int stepsx) {
            reach = ix;
            jumpLen = valuex;
            steps = stepsx;
        }
    }
}
