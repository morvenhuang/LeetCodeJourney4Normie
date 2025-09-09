package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an integer rowIndex, return the rowIndexth row of the Pascal’s triangle.
 * Notice that the row index starts from 0.
 * In Pascal’s triangle, each number is the sum of the two numbers directly above it.
 * <p>
 * Follow up:
 * Could you optimize your algorithm to use only O(k) extra space?
 * <p>
 * Example 1:
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * <p>
 * Example 2:
 * Input: rowIndex = 0
 * Output: [1]
 * <p>
 * Example 3:
 * Input: rowIndex = 1
 * Output: [1,1]
 * <p>
 * Constraints:
 * 0 <= rowIndex <= 33
 */
public class _0119e_PascalTriangleII {

    @Test
    public void test01() {
        int[] r = slt(3);
        int[] expected = {1, 3, 3, 1};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    int[] slt(int a) {
        int[] r = new int[a + 1];
        r[0] = 1;
        for (int i = 1; i <= a; i++) {
            int pre = 0;
            for (int j = 0; j < i + 1; j++) {
                int tmp = r[j];
                r[j] = pre + r[j];
                pre = tmp;
            }
        }
        return r;
    }
}
