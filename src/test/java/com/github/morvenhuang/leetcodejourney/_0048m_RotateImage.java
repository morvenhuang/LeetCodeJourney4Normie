package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * You have to rotate the image  in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class _0048m_RotateImage {

    @Test
    public void test01() {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expected = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        slt2(a);
        Assertions.assertTrue(TestHelper.same2DArrays(expected, a, false, false));
    }

    @Test
    public void test02() {
        int[][] a = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        int[][] expected = {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        };
        slt2(a);
        Assertions.assertTrue(TestHelper.same2DArrays(expected, a, false, false));
    }

    // 这个解法就没难度，但不符和题目中不得另外创建数组的要求。
    int[][] slt(int[][] a) {
        int n = a.length;
        int[][] r = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int col = n - i - 1;
                int row = j;
                r[row][col] = a[i][j];
            }
        }
        return r;
    }

    // 将这个正方形逐圈处理
    void slt2(int[][] a) {
        int len = a.length;
        for (int i = 0; i < len / 2; i++) { // 圈的数量
            for (int j = i; j < len - 1 - i; j++) {  // 圈的宽度
                int t = a[i][j]; // up
                int r = a[j][len - 1 - i]; // right
                int b = a[len - 1 - i][len - 1 - j]; // bottom
                int l = a[len - 1 - j][i]; // left
                a[i][j] = l;
                a[j][len - 1 - i] = t;
                a[len - 1 - i][len - 1 - j] = r;
                a[len - 1 - j][i] = b;
            }
        }
    }
}
