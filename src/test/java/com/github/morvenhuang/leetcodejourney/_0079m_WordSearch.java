package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where “adjacent” cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class _0079m_WordSearch {

    @Test
    public void test01() {
        char[][] a = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean r = slt(a, "ABCCED");
        Assertions.assertTrue(r);
    }

    @Test
    public void test02() {
        char[][] a = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean r = slt(a, "SEE");
        Assertions.assertTrue(r);
    }

    @Test
    public void test03() {
        char[][] a = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean r = slt(a, "ABCB");
        Assertions.assertTrue(!r);
    }

    @Test
    public void test04() {
        char[][] a = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean r = slt(a, "FCED");
        Assertions.assertTrue(r);
    }

    // DFS？
    // 就是从一个坐标，往上下左右四个方向延伸，问题是，如何避免一个坐标被重复处理。
    boolean slt(char[][] a, String target) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (process(a, i, j, target, 0, new HashSet<>())) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean process(char[][] a, int i, int j, String target, int charIndex, Set<String> set) {
        if (i < 0 || j < 0 || i >= a.length || j >= a[0].length) {
            return false;
        }
        if (set.contains(i + "_" + j)) {
            return false;
        }
        if (a[i][j] != target.charAt(charIndex)) {
            return false;
        }
        if (charIndex == target.length() - 1) {
            return true;
        }
        set.add(i + "_" + j);
        List<Position> next = new ArrayList<>();
        next.add(new Position(i, j - 1));
        next.add(new Position(i, j + 1));
        next.add(new Position(i - 1, j));
        next.add(new Position(i + 1, j));

        for (Position position : next) {
            if (process(a, position.i, position.j, target, charIndex + 1, set)) {
                return true;
            }
        }
        return false;
    }

    static class Position {
        int i;
        int j;

        public Position(int ix, int jx) {
            i = ix;
            j = jx;
        }
    }
}
