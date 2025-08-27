package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * A sudoku solution must satisfy all of the following rules:
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * <p>
 * Note:
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
public class _0037h_SudokuSolver {

    @Test
    public void test01() {
        int[][] a = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        int[][] expected = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        slt3(a);
        Assertions.assertTrue(check(expected, a));
    }

    boolean check(int[][] expected, int[][] a) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (expected[i][j] != a[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * （这里简化一点，使用二维 int 数组（0表示空值），而不是原题中使用的二维 string 数组）
     * <p>
     * 如果解过数独的都知道，对于简单的数独，基本思路就是，先找出可确定的，填上，然后再在当前基础上，再找出能确定的，以此一步步推进。
     * 最简单的想法，就是对于空格，排除同一行、同一列、同一小正方中已出现的值，如果排除之后，只剩下唯一的值，则这个空格可以确定了。
     * 然后重复这个过程。
     * <p>
     * 这个解法有一些问题：
     * 1. 效率低效，有很多重复的过程。
     * 2. 只能解决一些比较简单的数独。难度较高的数独，需要结合若干种排除手段，这个解法无法完成。（可以找一些在线的数独网站，看看难度等级是"困难"的题）
     */
    void slt(int[][] a) {
        while (true) {
            int solved = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int v = a[i][j];
                    if (v == 0) {
                        List<Integer> validValues = getValidValues(a, i, j);
                        if (validValues.size() == 1) {
                            a[i][j] = validValues.get(0);
                            System.out.println("i: " + i + ", j: " + j + ", v: " + a[i][j]);
                            solved++;
                        }
                    }
                }
            }
            if (solved == 0) {
                break;
            }
        }
    }

    /**
     * 人在做数独的时候，有多种方法进行配合，电脑算法来实现是比较繁琐的。
     * 电脑最直观的做法还是：暴力尝试+回溯。专业一点说：深度优先搜索。
     */
    void slt2(int[][] a) {
        // 先收集待填的单元格坐标
        List<Tuple2> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j] == 0) {
                    list.add(new Tuple2(i, j));
                }
            }
        }

        process(a, list, 0);
    }

    boolean process(int[][] a, List<Tuple2> list, int listIndex) {
        if (listIndex == list.size()) {
            // 只有前面所有待填单元格都成功了，才可能到这里
            return true;
        }

        Tuple2 tuple2 = list.get(listIndex);
        for (int v = 1; v <= 9; v++) { // 逐个尝试1~9
            if (chk(a, tuple2.i, tuple2.j, v)) {
                a[tuple2.i][tuple2.j] = v;
                // 递归，对下一待填单元格进行处理
                boolean ret = process(a, list, listIndex + 1);
                if (ret) {
                    // 这里很重要，就是要在找到答案之后，马上停止，否则 for 循环会继续
                    return true;
                } else {
                    // 这里也很重要，下一待填单元格填充失败，需要回溯，在这个过程中，之前尝试填入的值也要回退成0.
                    a[tuple2.i][tuple2.j] = 0;
                }
            }
        }
        return false;
    }

    /**
     * 如果抓大放小，忽略一些 slt2 中一些小的优化，整体的代码逻辑会更易读。
     * <p>
     * 另外，递归是比较绕的，要学会简化易懂的去理解，例如下面，在递归时，想的是"解决剩下的数独"这么个整体问题，不需要在深入进第二层、第三层了，
     * 人的思维不擅长递归，容易把自己绕晕。
     */
    boolean slt3(int[][] a) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j] == 0) {
                    for (int v = 1; v <= 9; v++) {
                        if (chk(a, i, j, v)) {
                            a[i][j] = v;

                            // 递归尝试解决剩下的数独
                            if (slt3(a)) {
                                // （1）如果找到解，返回true
                                return true;
                            } else {
                                // （2）回溯，重置为0
                                a[i][j] = 0;
                            }
                        }
                    }
                    // （3）如果到这里，说明该单元格填入 1~9 都不行，但凡有合适的数，就会在（1）处返回。
                    return false;
                }
            }
        }
        // （4）所有单元格都已填满，解决成功
        return true;
    }

    static class Tuple2 {
        int i;
        int j;

        public Tuple2(int ix, int jx) {
            i = ix;
            j = jx;
        }
    }

    boolean chk(int[][] a, int row, int col, int v) {
        return chkRowValues(a, row, col, v) && chkColValues(a, row, col, v) && chkBoxValues(a, row, col, v);
    }

    boolean chkRowValues(int[][] a, int row, int col, int v) {
        for (int x = 0; x < 9; x++) {
            if (v == a[row][x] && col != x) {
                return false;
            }
        }
        return true;
    }

    boolean chkColValues(int[][] a, int row, int col, int v) {
        for (int x = 0; x < 9; x++) {
            if (v == a[x][col] && row != x) {
                return false;
            }
        }
        return true;
    }

    boolean chkBoxValues(int[][] a, int row, int col, int v) {
        int r = row / 3;
        int c = col / 3;
        r *= 3;
        c *= 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (v == a[i][j] && row != i && col != j) {
                    return false;
                }
            }
        }
        return true;
    }

    List<Integer> getValidValues(int[][] a, int i, int j) {
        List<Integer> candidates = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        candidates.removeAll(getRowValues(a, i));
        candidates.removeAll(getColValues(a, j));
        candidates.removeAll(getBoxValues(a, i, j));
        return candidates;
    }

    List<Integer> getRowValues(int[][] a, int row) {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            int v = a[row][x];
            if (v != 0) {
                list.add(v);
            }
        }
        return list;
    }

    List<Integer> getColValues(int[][] a, int col) {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < 9; x++) {
            int v = a[x][col];
            if (v != 0) {
                list.add(v);
            }
        }
        return list;
    }

    List<Integer> getBoxValues(int[][] a, int row, int col) {
        List<Integer> list = new ArrayList<>();
        int r = row / 3;
        int c = col / 3;
        r *= 3;
        c *= 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                int v = a[i][j];
                if (v != 0) {
                    list.add(v);
                }
            }
        }
        return list;
    }


}
