package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given n non-negative integers a1, a2, …, an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * 给定 n 个非负整数 a1, a2, …, an ，其中每个整数代表坐标 (i, ai) 处的点。
 * 绘制 n 条垂直线，使线 i 的两个端点分别位于 (i, ai) 和 (i, 0)。找出两条线，它们与 x 轴一起构成一个容器，并且该容器中装有最多的水。
 * <p>
 * Note: You may not slant the container and n is at least 2.
 * 注意 ：您可能不会倾斜容器并且 n 至少为 2。
 * <p>
 * （举例）The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case,
 * the max area of water (blue section) the container can contain is 49.
 * 上述垂直线用数组 [1,8,6,2,5,4,8,3,7] 表示。在这种情况下，容器可容纳的最大水面积（蓝色部分）为 49。
 */
public class _0011m_ContainerWithMostWater {

    @Test
    public void test01() {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int max = slt(a);
        Assertions.assertEquals(max, 49);
    }

    @Test
    public void test02() {
        int[] a = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int max = slt2(a);
        Assertions.assertEquals(max, 49);
    }

    @Test
    public void test03() {
        int[] a = new int[]{1, 1, 1, 8, 8, 1, 1};
        int max = slt2(a);
        Assertions.assertEquals(max, 8);
    }

    // 最直接做法，遍历计算所有组合，时间复杂度高：O(n^2)
    int slt(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 1; j < a.length; j++) {
                int w = (j - i) * Math.min(a[i], a[j]);
                if (w > max) {
                    max = w;
                }
            }
        }
        return max;
    }

    /**
     * 假如想提高效率，例如时间复杂度降低到：O(n)，意味着只能有一个循环，这在算法题中，有一个比较常见的思路，就是两个指针，从头尾相向而行。
     *
     * 为什么能想到这个思路？
     * 1. 双指针是比较常见的思路，想要提高效率的时候可以尝试，同样比较常见的思路还有比如哈希表
     * 2. 题目的装水量，涉及桶宽和桶高两个因素，我们可以先从极大化桶宽开始。
     *
     * 本题中，需要考虑清楚，为什么这种做法可以找到最大面积？
     */
    int slt2(int[] a) {
        int max = 0;
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            int w = (r - l) * Math.min(a[l], a[r]);
            if (w > max) {
                max = w;
            }
            // 为什么是移动较小的那边的指针？
            // 因为只有移动较小的那边的指针，面积才有增大的可能性（即有可能提高桶高），当然只是可能。
            // 如果移动较大的那边的指针，由于桶高始终受限于较小的那边，面积只可能变小（桶宽收缩）。
            if (a[l] < a[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}
