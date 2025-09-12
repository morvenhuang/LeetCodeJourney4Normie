package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * Input: [2,2,1]
 * Output: 1
 * <p>
 * Example 2:
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class _0136e_SingleNumber {

    @Test
    void test01() {
        int[] a = {2, 2, 1};
        int r = slt(a);
        Assertions.assertEquals(1, r);
    }

    @Test
    void test02() {
        int[] a = {4, 1, 2, 1, 2};
        int r = slt(a);
        Assertions.assertEquals(4, r);
    }

    // 这个因为看过相关例子，在加上体中又说要时间复杂度 O(n)，且不能用额外的空间。
    // 《编程珠玑》上好像有这个例子。
    int slt(int[] a) {
        int r = a[0];
        for (int i = 1; i < a.length; i++) {
            r = r ^ a[i]; // 异或运算
        }
        return r;
    }
}
