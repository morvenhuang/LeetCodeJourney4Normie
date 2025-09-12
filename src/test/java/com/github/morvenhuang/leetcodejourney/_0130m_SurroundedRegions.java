package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * <p>
 * Explanation:
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped
 * to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class _0130m_SurroundedRegions {

    @Test
    void test01() {
        int[][] a = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 1}
        };
        slt2(a);
        int[][] expected = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 0, 1, 1}
        };
        Assertions.assertTrue(TestHelper.same2DArrays(expected, a, false, false));
    }

    @Test
    void test02() {
        int[][] a = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 0, 0, 1}
        };
        slt2(a);
        int[][] expected = {
                {1, 1, 1, 1},
                {1, 0, 0, 1},
                {1, 1, 0, 1},
                {1, 0, 0, 1}
        };
        Assertions.assertTrue(TestHelper.same2DArrays(expected, a, false, false));
    }

    @Test
    void test03() {
        int[][] a = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        slt2(a);
        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        Assertions.assertTrue(TestHelper.same2DArrays(expected, a, false, false));
    }

    // 简答起见，用二维 int 数组，1代替原来的 X
    // 比较简单的想法：用一个 hash set 记录不被包围的0
    void slt(int[][] a) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                // 遇到位于四个边上的0，则从这个0开始向上下左右四个方向找0
                if (i == 0 || j == 0 || i == a.length - 1 || j == a[0].length - 1) {
                    if (a[i][j] == 0) {
                        String s = i + "_" + j;
                        if (!set.contains(s)) {
                            process(a, set, i, j);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0 && !set.contains(i + "_" + j)) {
                    a[i][j] = 1;
                }
            }
        }
    }

    void process(int[][] a, Set<String> set, int i, int j) {
        if (i < 0 || j < 0 || i >= a.length || j >= a[0].length || set.contains(i + "_" + j) || a[i][j] == 1) {
            return;
        }
        set.add(i + "_" + j);
        process(a, set, i - 1, j); // up
        process(a, set, i + 1, j); // down
        process(a, set, i, j - 1); // left
        process(a, set, i, j + 1); // right
    }

    // 回溯
    void slt2(int[][] a) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0 && !set.contains(i + "_" + j)) {
                    process2(a, set, i, j);
                }
            }
        }
    }

    boolean process2(int[][] a, Set<String> set, int i, int j) {
        if (i == 0 || i == a.length - 1 || j == 0 || j == a[0].length - 1) {
            if (a[i][j] == 0) {
                set.add(i + "_" + j);
                return false;
            }
        }
        set.add(i + "_" + j);
        a[i][j] = 1;
        if (i > 0 && a[i - 1][j] == 0) {
            if (!process2(a, set, i - 1, j)) {
                a[i][j] = 0; // 回溯
                return false;
            }
        }
        if (i < a.length - 1 && a[i + 1][j] == 0) {
            if (!process2(a, set, i + 1, j)) {
                a[i][j] = 0;
                return false;
            }
        }
        if (j > 0 && a[i][j - 1] == 0) {
            if (!process2(a, set, i, j - 1)) {
                a[i][j] = 0;
                return false;
            }
        }
        if (j < a[0].length - 1 && a[i][j + 1] == 0) {
            if (!process2(a, set, i, j + 1)) {
                a[i][j] = 0;
                return false;
            }
        }
        return true;
    }

}
