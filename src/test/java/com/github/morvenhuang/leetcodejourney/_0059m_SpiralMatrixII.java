package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Example:
 * Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class _0059m_SpiralMatrixII {

    @Test
    public void test01() {
        int[][] r = slt(3);
        int[][] expected = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}};
        Assertions.assertTrue(TestHelper.same2DArrays(expected, r, false, false));
    }

    @Test
    public void test02() {
        int[][] r = slt(4);
        int[][] expected = {
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}};
        Assertions.assertTrue(TestHelper.same2DArrays(expected, r, false, false));
    }

    // 从 0054 拷贝过来，稍微改改就行了
    int[][] slt(int n) {
        int[][] a = new int[n][n];
        process(a, 0, 1);
        return a;
    }

    // level, zero-based
    void process(int[][] a, int level, int v) {
        int w = a[0].length;
        int h = a.length;
        if (level == h - 1 - level) { // 一维数组，一行
            for (int col = level; col <= w - 1 - level; col++) {
                a[level][col] = v;
                v++;
            }
            return;
        }

        for (int col = level; col <= w - level - 2; col++) { // 上
            a[level][col] = v;
            v++;
        }

        for (int row = level; row <= h - level - 2; row++) { // 右
            a[row][w - level - 1] = v;
            v++;
        }

        for (int col = w - level - 1; col >= level + 1; col--) { // 下
            a[h - level - 1][col] = v;
            v++;
        }

        for (int row = h - level - 1; row >= level + 1; row--) { // 左
            a[row][level] = v;
            v++;
        }

        if (level <= (w + 1) / 2) {
            process(a, level + 1, v);
        }
    }
}
