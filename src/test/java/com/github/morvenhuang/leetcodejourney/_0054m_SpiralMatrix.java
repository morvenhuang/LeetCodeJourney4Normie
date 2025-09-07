package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Example 2:
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class _0054m_SpiralMatrix {

    @Test
    public void test01() {
        int[][] a = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        List<Integer> r = slt(a);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5), r);
    }

    @Test
    public void test02() {
        int[][] a = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        List<Integer> r = slt(a);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7), r);
    }

    // 最烦这种题目
    List<Integer> slt(int[][] a) {
        List<Integer> r = new ArrayList<>();
        process(a, 0, r);
        return r;
    }

    // level, zero-based
    void process(int[][] a, int level, List<Integer> finalResult) {
        int w = a[0].length;
        int h = a.length;
        if (level == h - 1 - level) { // 一维数组，一行
            for (int col = level; col <= w - 1 - level; col++) {
                finalResult.add(a[level][col]);
            }
            return;
        }
        if (level == w - 1 - level) { // 一维数组，一列
            for (int row = level; row <= h - 1 - level; row++) {
                finalResult.add(a[row][level]);
            }
            return;
        }
        for (int col = level; col <= w - level - 2; col++) { // 上
            finalResult.add(a[level][col]);
        }

        for (int row = level; row <= h - level - 2; row++) { // 右
            finalResult.add(a[row][w - level - 1]);
        }

        for (int col = w - level - 1; col >= level + 1; col--) { // 下
            finalResult.add(a[h - level - 1][col]);
        }

        for (int row = h - level - 1; row >= level + 1; row--) { // 左
            finalResult.add(a[row][level]);
        }

        if (level <= (Math.min(w, h) + 1) / 2) {
            process(a, level + 1, finalResult);
        }
    }
}
