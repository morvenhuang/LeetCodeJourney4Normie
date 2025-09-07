package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens’ placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * Example:
 * Input: 4
 * Output: [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 * <p>
 * 1）N-皇后，是指棋盘为 N*N，且皇后为 N 个。
 * 2）皇后的攻击范围：所在行、所在列、所在45度斜线、所在135度斜线，整体类似"米"字形。
 */
public class _0051h_NQueens {

    @Test
    public void test01() {
        List<int[][]> r = slt(4);
        Assertions.assertEquals(2, r.size());
        int[][] a1 = {{0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 0, 0, 0},
                {0, 0, 1, 0}};
        int[][] a2 = {{0, 0, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 1, 0, 0}};
        Assertions.assertTrue(TestHelper.same2DArrays(a1, r.get(0), false, false));
        Assertions.assertTrue(TestHelper.same2DArrays(a2, r.get(1), false, false));
    }

    // 简单起见，用二维整型数组，0表示空位，1表示皇后
    List<int[][]> slt(int n) {
        List<int[][]> r = new ArrayList<>();
        process(new int[n][n], 0, r);
        return r;
    }

    void process(int[][] a, int row, List<int[][]> finalResult) {
        if (row > a.length - 1) {
            int[][] cp = copy2DArray(a);
            finalResult.add(cp);
            return;
        }
        boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            if (checkPosition(a, row, i)) {
                flag = true;
                a[row][i] = 1;
                process(a, row + 1, finalResult);
                a[row][i] = 0; // 回溯
            }
        }
        if (!flag) {
            return;
        }
    }

    boolean checkPosition(int[][] a, int i, int j) {
        int len = a.length;

        // same column
        for (int r = 0; r < len; r++) {
            if (r == i) {
                continue;
            }
            if (a[r][j] == 1) {
                return false;
            }
        }

        // right
        for (int x = j + 1; x < len; x++) {
            if (a[i][x] == 1) {
                return false;
            }
            if (i - x + j >= 0 && a[i - x + j][x] == 1) { // 右上斜线
                return false;
            }
            if (i + x - j < len && a[i + x - j][x] == 1) { // 右下斜线
                return false;
            }
        }

        for (int x = 0; x < j; x++) {
            if (a[i][x] == 1) {
                return false;
            }
            if (i - j + x >= 0 && a[i - j + x][x] == 1) {
                return false;
            }
            if (i + j - x < len && a[i + j - x][x] == 1) {
                return false;
            }
        }


        return true;
    }

    int[][] copy2DArray(int[][] a) {
        int len = a.length;
        int[][] r = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                r[i][j] = a[i][j];
            }
        }
        return r;
    }
}
