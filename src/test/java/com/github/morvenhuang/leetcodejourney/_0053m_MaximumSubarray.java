package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class _0053m_MaximumSubarray {

    @Test
    public void test01(){
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        int r = slt(a);
        Assertions.assertEquals(6,r);
    }

    /**
     * （这道题我没想出来，我知道可以用dp（动态规划），但里面的关键点没有想通。）
     * 实际上，最大子数组问题的动态规划解法有一个专门的名称，Kadane 算法。
     * 这里面的一个关键点是：子数组的结尾元素，总是位于原数组范围内。
     * 问题转变成：如果我必须选择一个以当前数字（含）结尾的子数组，那么能得到的最大和是多少？
     *
     * 我们使用 for 循环遍历了一遍数组，也就是分别考虑了以数组中各元素结尾的情况，实际上也就考虑了所有情况。
     *
     * 以下代码中的 maxCurrent 表示包含当前元素 nums[i]，并且以 nums[i] 为结尾的所有连续子数组中的最大 sum。
     */
    int slt(int[] a) {
        int maxCurrent = a[0];
        int maxGlobal = a[0];

        for (int i = 1; i < a.length; i++) {
            // 修改前的 maxCurrent 表示所有以 a[i-1] 结尾的子数组中的最大 sum
            // 此时，如果我们要得到所有以 a[i] 结尾的子数组中的最大 sum，那么有2种选择：
            // 1）和前面那个 a[i-1] 为结尾的数组连起来
            // 2）单独以 a[i] 作为长度为1的子数组
            // 那就看二者谁比较大了。
            maxCurrent = Math.max(a[i], maxCurrent + a[i]);
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
            }
        }

        return maxGlobal;
    }
}
