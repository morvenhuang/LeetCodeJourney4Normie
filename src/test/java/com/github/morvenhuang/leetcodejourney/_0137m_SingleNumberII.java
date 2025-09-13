package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * Input: [2,2,3,2]
 * Output: 3
 * <p>
 * Example 2:
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class _0137m_SingleNumberII {

    @Test
    void test01() {
        int[] a = {2, 2, 3, 2};
        int r = slt2(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    void test02() {
        int[] a = {0, 1, 0, 1, 0, 1, 9};
        int r = slt2(a);
        Assertions.assertEquals(9, r);
    }

    @Test
    void test03() {
        int[] a = {0, 1, 0, 1, 0, 1, -9};
        int r = slt2(a);
        Assertions.assertEquals(-9, r);
    }

    /**
     * 首先肯定想和 0136 一样，能不能用位运算，好像不太行？
     * 其实如果可以用额外空间，好做。
     * <p>
     * 在保证 O(n) 时间复杂度的前提下：
     * 1. 两次或三次遍历，能不能解？
     * 2. 双指针？感觉没什么用。
     * 3. 将数量信息存储到现有数组中？
     * <p>
     * 没思路。
     * 这个题的解法需要巧妙的位运算设计，一般人很难想到。
     * <p>
     * 这里的 once，举例说明，0011，表示第0为出现过1次1、第1位出现过1次1
     * 这里的 twice，举例说明，0011，表示第0为出现过2次1、第1为出现过2次1
     */
    int slt(int[] a) {
        int once = 0;
        int twice = 0;
        for (int num : a) {
            once = (once ^ num) & ~twice;
            twice = (twice ^ num) & ~once;
        }
        return once;
    }

    // 受上面经典解法的启发，来一个比较土，但好理解的版本
    int slt2(int[] a) {
        int[] bitTimes = new int[32]; // 用一个32位的数组来存储整型的每一位出现1的次数（有点不太符合题目中不使用额外内存的要求，但好在这里使用的内存是一个常量）
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < 32; j++) {
                int v = (a[i] >> j) & 1;
                if (v == 1) {
                    bitTimes[31 - j] = bitTimes[31 - j] + 1;
                    if (bitTimes[31 - j] == 3) {
                        bitTimes[31 - j] = 0;
                    }
                }
            }
        }

        // 一个32个元素的数组（元素值只能是0或1），作为某一个整型的二进制表示。现在从这个二进制表示，还原出这个整型。主要数组的0元素，表示的是二进制最高位，以此类推，不要弄反了。
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r |= (bitTimes[i] << (31 - i));
        }
        return r;
    }
}
