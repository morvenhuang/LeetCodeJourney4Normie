package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * <p>
 * Example 1:
 * Input:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * <p>
 * Example 2:
 * Input:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * <p>
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 * modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * <p>
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 */
public class _0036m_ValidSudoku {

    @Test
    public void test01() {
        String[][] a = {
                {"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}
        };
        boolean ret = slt(a);
        Assertions.assertTrue(ret);
    }

    @Test
    public void test02() {
        String[][] a = {
                {"8", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}
        };
        boolean ret = slt(a);
        Assertions.assertTrue(!ret);
    }

    /**
     * 数独游戏的规则，大家可以上网搜索了解一下，还是比较简单。这个题目只是要验证数独题目有没有问题，不是要解这个数独。
     * 最直接的想法，就是验证一下每一行是不是都是1-9，且没有重复；每一列是不是都是1-9，且没有重复；每一个小正方是不是都是1-9，且没有重复。
     * 这个时间复杂度是 O(n^2)，不过本题中数组大小固定，所以时间复杂度时间是 O(1)。
     * <p>
     * 有一些更简练一点的写法，但也都是需要两层嵌套循环。（如有必要，后续再考虑优化更新吧）
     * <p>
     * 这道题虽然标识为中等难道，但我认为应该是 easy 级别。
     */
    boolean slt(String[][] a) {
        Set<String> all = new HashSet<>(Arrays.asList(".", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            Set<String> aCol = new HashSet<>();
            Set<String> aRow = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                // 判断某一行
                String val1 = a[i][j];
                if (!all.contains(val1)) {
                    return false;
                }
                if (aRow.contains(val1) && !".".equals(val1)) {
                    return false;
                }
                aRow.add(val1);

                // 判断某一列
                String val2 = a[j][i];
                if (!all.contains(val2)) {
                    return false;
                }
                if (aCol.contains(val2) && !".".equals(val2)) {
                    return false;
                }
                aCol.add(val2);

                // 判断各个小正方形（3x3）
                String key = i / 3 + "_" + j / 3;
                if (map.containsKey(key)) {
                    if (map.get(key).contains(a[i][j]) && !".".equals(a[i][j])) {
                        return false;
                    }
                    map.get(key).add(a[i][j]);
                } else {
                    Set<String> x = new HashSet<>();
                    x.add(a[i][j]);
                    map.put(key, x);
                }
            }
        }


        return true;
    }
}
