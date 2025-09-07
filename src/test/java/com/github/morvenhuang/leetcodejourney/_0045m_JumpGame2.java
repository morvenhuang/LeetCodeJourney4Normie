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
        int r = slt3(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test02() {
        int[] a = {2, 3, 0, 1, 4};
        int r = slt3(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test03() {
        int[] a = {2, 3, 3, 1, 4, 2};
        int r = slt3(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    public void test04() {
        int[] a = {1, 1, 1, 1};
        int r = slt3(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    public void test05() {
        int[] a = {2, 2, 3, 5, 1, 1, 2};
        int r = slt2(a);
        Assertions.assertEquals(3, r);
    }

    /**
     * 递归，当数组长度过大的时候，不适用
     */
    int slt(int[] a) {
        return process(a, 0);
    }

    int process(int[] a, int i) {
        if (a[i] == 0) { // 如果当前值是0，则无法前进，因此返回一个特殊值-1做标识。
            return -1;
        } else if (a[i] >= a.length - i - 1) { // 当期值一步可达数组最末尾。
            return 1;
        } else {
            int min = 1000; // 题目限定数组长度不大于1000.
            for (int j = 1; j <= a[i]; j++) { // 举例：如果当前值是5，则可能的跳跃距离是1，2，3，4，5，这里用 for 循环遍历。
                int r = process(a, i + j);
                if (r > 0 && r < min) {
                    min = r;
                }
            }
            return min + 1;
        }
    }

    /**
     * 很多情况下，如果递归由于层数过多不适用的情况下，可以考虑使用栈（数据结构）来处理。
     */
    int slt2(int[] a) {
        // 初始化，对数组中的第一个正整数进行处理，举例：假设这个数在a[0]，值是5，那么将可能的跳跃长度，及步数（第一次条，步数都是1）
        // 都放入栈，即：(1,1), (2,1), (3,1), (4,1), (5,1)
        Stack<Tuple2> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) { // ignore zero
                continue;
            }
            if (i + a[i] >= a.length - 1) { // 如果步数足够多，直接返回1
                return 1;
            }
            for (int j = 1; j <= a[i]; j++) {
                stack.push(new Tuple2(i + j, 1));
            }
            break;
        }

        int min = Integer.MAX_VALUE;
        while (!stack.empty()) {
            Tuple2 pop = stack.pop();
            if (pop.reach == 0) {
                continue;
            }
            if (pop.reach + a[pop.reach] >= a.length - 1) { // 下一步可达最末尾
                if (pop.steps + 1 < min) {
                    min = pop.steps + 1;
                }
            } else {
                // 如果下一步不能达到最末尾，那么将下一步可能达到的所有位置都压入栈中
                for (int j = 1; j <= a[pop.reach]; j++) {
                    stack.push(new Tuple2(pop.reach + j, pop.steps + 1));
                }
            }
        }
        return min;
    }

    /**
     * 学习一下：贪心算法。
     * 贪心算法（Greedy Algorithm）的核心思想非常直观和“人性化”：在每一步选择中都采取当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。
     * 它要求我们在做选择时，可以只考虑当前情况，而不需要考虑子问题的解。这就像是“吃了这顿不管下顿”，但最后发现每顿都吃好，一辈子也就过好了。
     * <p>
     * 不过，这道题的局部最优解，不是那么直观。
     * 它实际上是："从当前位置，一跳可达的位置中，选出最优"
     * 由于我们要尽快到达末尾，因此这里的"选出最优"，实际上是"选出可达最远"
     * 即：假设你身处 a[i] 位置，从当前位置可达的位置（或者说它所覆盖的范围），选一个下一次的起跳点，该起跳点的覆盖范围最远。
     * <p>
     * 时间复杂度是：O(n)~O(n^2)?。
     * <p>
     * 网上有更为简洁且O(n)的写法，但个人认为下面的写法，对于理解"局部最优解"有帮助。
     */
    int slt3(int[] a) {
        int steps = 0;
        int i = 0;

        while (i <= a.length - 2) {
            int max = 0;
            int index = -1;
            // range: i+1 -- i+a[i]
            if (i + a[i] >= a.length - 1) { // 已达数组最末尾
                return steps + 1;
            }
            for (int j = i + 1; j <= i + a[i]; j++) {
                if (j + a[j] > max) {
                    max = j + a[j];
                    index = j;
                }
            }
            steps++;
            i = index;
        }
        return steps;
    }

    static class Tuple2 {
        int reach; // 当前位置一次跳跃可达哪里
        int steps; // 总步数

        public Tuple2(int ix, int stepsx) {
            reach = ix;
            steps = stepsx;
        }
    }
}
