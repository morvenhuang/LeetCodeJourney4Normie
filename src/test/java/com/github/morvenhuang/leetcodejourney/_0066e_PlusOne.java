package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * <p>
 * Example 2:
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 */
public class _0066e_PlusOne {

    @Test
    public void test01() {
        int[] a = {1, 2, 3};
        int[] r = slt(a);
        int[] expected = {1, 2, 4};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    @Test
    public void test02() {
        int[] a = {4, 3, 2, 1};
        int[] r = slt(a);
        int[] expected = {4, 3, 2, 2};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    @Test
    public void test03() {
        int[] a = {9, 9, 9};
        int[] r = slt(a);
        int[] expected = {1, 0, 0, 0};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    // 这个简单，就是要注意一下进位
    int[] slt(int[] a) {
        int n = a.length;
        int[] r = new int[n + 1];

        int carry = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1 || carry == 1) {
                int sum = a[i] + 1;
                if (sum < 10) {
                    r[i + 1] = sum;
                    carry = 0;
                } else {
                    r[n] = 0;
                    carry = 1;
                }
            } else {
                r[i + 1] = a[i];
                carry = 0;
            }
        }
        if (carry == 1) {
            r[0] = 1;
        }
        if (r[0] == 0) {
            return Arrays.copyOfRange(r, 1, r.length);
        } else {
            return r;
        }
    }
}
