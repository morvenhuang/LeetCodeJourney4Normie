package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A robot is located at the top-left corner of a m x n grid (marked ‘Start’ in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
 * corner of the grid (marked ‘Finish’ in the diagram below).
 * Now consider if some obstacles(障碍物) are added to the grids. How many unique paths would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * Note: m and n will be at most 100.
 * <p>
 * Example 1:
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class _0063m_UniquePaths2 {

    @Test
    public void test01() {
        int[][] a = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int r = slt(a);
        Assertions.assertEquals(2, r);
    }

    /**
     * 这题属于最简单的 dp 了，是作为 dp 学习的好例子。
     *
     * 对于坐标(i,j)的位置，到达它的路径有几条？
     * 由于运动方向只能是向下或者向右，所以只有左侧(i,j-1), 上方(i-1,j)这两个位置的点才有可能到达(i,j)，
     * 因此，到达(i,j)的路径数量，等于到达(i,j-1)的路径数量, 加上到达(i-1,j)的路径数量。
     * 当然，还要加上一些边界判断，以及障碍物判断。
     */
    int slt(int[][] a) {
        int w = a.length;
        int h = a[0].length;
        int[][] state = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 && j == 0) { // 起点，忽略
                    continue;
                }
                if (a[i][j] == 1) { // 障碍物
                    state[i][j] = 0;
                    continue;
                }
                // 初始化起点一步可达的位置，即第一行第二列，第二行第一列的值。后续所有的值可以根据此二者滚动推导出来。
                if ((i == 0 && j == 1) || i == 1 && j == 0) {
                    state[i][j] = 1;
                    continue;
                }
                if (i == 0) {
                    state[i][j] = state[i][j - 1];
                } else if (j == 0) {
                    state[i][j] = state[i - 1][j];
                } else {
                    state[i][j] = state[i][j - 1] + state[i - 1][j];
                }
            }
        }
        return state[h - 1][w - 1];
    }
}
