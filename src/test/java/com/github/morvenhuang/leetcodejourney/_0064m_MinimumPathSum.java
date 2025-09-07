package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the
 * sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Example:
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class _0064m_MinimumPathSum {

    @Test
    public void test01() {
        int[][] a = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int r = slt(a);
        Assertions.assertEquals(7, r);
    }

    /**
     * 这个参考 0063 即可
     */
    int slt(int[][] a) {
        int w = a[0].length;
        int h = a.length;
        int[][] state = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 && j == 0) {
                    state[i][j] = a[i][j];
                    continue;
                }
                if (i == 0) {
                    state[i][j] = state[i][j - 1] + a[i][j];
                } else if (j == 0) {
                    state[i][j] = state[i - 1][j] + a[i][j];
                } else {
                    state[i][j] = Math.min(state[i][j - 1], state[i - 1][j]) + a[i][j];
                }
            }
        }
        return state[h - 1][w - 1];
    }
}
