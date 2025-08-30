package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
 * it is able to trap after raining.
 * <p>
 * （原题有图）The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
 * (blue section) are being trapped.
 */
public class _0042h_TrappingRainWater {

    @Test
    public void test01() {
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int r = slt2(a);
        Assertions.assertEquals(6, r);
    }

    @Test
    public void testAllZeros() {
        int[] height = {0, 0, 0, 0};
        int expected = 0;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "All zeros test failed");
    }

    @Test
    public void testIncreasingHeights() {
        int[] height = {1, 2, 3, 4};
        int expected = 0;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Increasing heights test failed");
    }

    @Test
    public void testDecreasingHeights() {
        int[] height = {4, 3, 2, 1};
        int expected = 0;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Decreasing heights test failed");
    }

    @Test
    public void testSinglePeak() {
        int[] height = {3, 0, 3};
        int expected = 3;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Single peak test failed");
    }

    @Test
    public void testTwoPeaks() {
        int[] height = {3, 0, 2, 0, 4};
        int expected = 7;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Two peaks test failed");
    }

    @Test
    public void testFlatTerrain() {
        int[] height = {5, 5, 5, 5};
        int expected = 0;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Flat terrain test failed");
    }

    @Test
    public void testSingleElement() {
        int[] height = {5};
        int expected = 0;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Single element test failed");
    }

    @Test
    public void testComplexCase() {
        int[] height = {4, 2, 0, 3, 2, 5};
        int expected = 9;
        int actual = slt2(height);
        Assertions.assertEquals(expected, actual, "Complex case test failed");
    }

    /**
     * 通过画图，比较容易理解。
     * <p>
     * 最直观的解法：从头尾非0元素开始，如果这个范围内，有比当前桶高（即 min(a[head], a[tail])）还低的地方，则可以存水，存水量好计算，
     * 桶高减该块高度即可。然后，移动头/尾，继续。
     * <p>
     * 该解法时间复杂度较高：O(n^2)
     */
    int slt(int[] a) {
        int head = 0;
        int tail = a.length - 1;
        int sum = 0;
        while (head < tail) {
            if (a[head] == 0) {
                head++;
                continue;
            }
            if (a[tail] == 0) {
                tail--;
                continue;
            }
            int min = Math.min(a[head], a[tail]); // 桶的高度，等于左右两边比较低的那一边。
            for (int i = head + 1; i <= tail - 1; i++) {
                if (a[i] < min) {
                    sum += min - a[i]; // 对于比当前桶低的块，计算存水量
                    a[i] = min; // 将该处填充成与当前桶齐高，避免后续重复计算
                }
            }
            if (a[head] == a[tail]) {
                head++;
                tail--;
            } else if (a[head] > a[tail]) {
                tail--;
            } else {
                head++;
            }
        }
        return sum;
    }

    /**
     * 能不能到 O(n)？
     * 如果要  O(n)，可以有多个循环，但不能有嵌套循环。
     * 我想的是先确定一个边，于是通过一个循环查到最高柱子的高度以及它的位置。
     * 那么，用这个柱子就能将原来的数组分成左右两个部分，左半区间的右侧桶板高度是确定的；右半区间的左侧桶板高度是确定的。
     * 分开来处理左右两个区间。
     * 当处理左区间时，一个循环里需要做两件事：1）到目前为止的左侧最高柱子，2）计算当前柱子上方可以存多少水。
     * 个人觉得这个做法，比网上很多 O(n) 的解法好理解一些。
     */
    int slt2(int[] a) {
        Tuple2 max = getMax(a);

        int sum = 0;
        int leftMax = a[0];
        for (int i = 1; i < max.j; i++) {
            if (a[i] >= leftMax) {
                leftMax = a[i]; // 当前柱子是目前为止最高的柱子，无法存水
            } else {
                sum += leftMax - a[i];
            }
        }

        int rightMax = a[a.length - 1];
        for (int i = a.length - 2; i > max.j; i--) {
            if (a[i] >= rightMax) {
                rightMax = a[i];
            } else {
                sum += rightMax - a[i];
            }
        }

        return sum;
    }

    Tuple2 getMax(int[] a) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= max) { // 如果最大值有重复，保留最右侧那个最大值的下标
                max = a[i];
                index = i;
            }
        }
        return new Tuple2(max, index);
    }

    static class Tuple2 {
        int i;
        int j;

        public Tuple2(int ix, int jx) {
            i = ix;
            j = jx;
        }
    }
}
