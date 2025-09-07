package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * <p>
 * Example 1:
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * <p>
 * Example 2:
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 */
public class _0074m_Searcha2DMatrix {

    @Test
    public void test01() {
        int[][] a = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        boolean r = slt(a, 3);
        Assertions.assertTrue(r);
    }

    @Test
    public void test02() {
        int[][] a = {
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        boolean r = slt(a, 13);
        Assertions.assertTrue(!r);
    }

    /**
     * 最简单的想法，就是将2维数组转成1维，这样就直接用二分法了。
     * 不过转的过程，时间复杂度就 O(n) 了，到不了二分法的 O(log n)
     * <p>
     * 想要保持 O(log n)，那就只能在2维数组内进行二分了，也能算，就是比较复杂一点。
     */
    boolean slt(int[][] a, int target) {
        int l = 0;
        int r = a.length * a[0].length - 1;
        int w = a[0].length;
        while (l <= r) {
            int m = l + (r - l) / 2;
            Position position = getPosition(m, w);
            int v = a[position.i][position.j];
            if (v == target) {
                return true;
            } else if (v > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    Position getPosition(int x, int width) {
        Position position = new Position();
        if ((x + 1) % width == 0) {
            position.i = (x + 1) / width - 1;
            position.i = Math.max(0, position.i);
            position.j = width - 1;
            return position;
        }
        position.i = (x + 1) / width;
        position.j = (x + 1) % width - 1;
        return position;
    }

    static class Position {
        int i;
        int j;
    }
}
