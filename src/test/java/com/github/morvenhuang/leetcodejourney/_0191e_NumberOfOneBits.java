package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a positive integer n, write a function that returns the number of set bits in its binary representation
 * (also known as the Hamming weight).
 * <p>
 * Example 1:
 * Input: n = 11
 * Output: 3
 * Explanation:
 * The input binary string 1011 has a total of three set bits.
 * <p>
 * Example 2:
 * Input: n = 128
 * Output: 1
 * Explanation:
 * The input binary string 10000000 has a total of one set bit.
 * <p>
 * Example 3:
 * Input: n = 2147483645
 * Output: 30
 * Explanation:
 * The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
 * <p>
 * Constraints:
 * 1 <= n <= 2^31 - 1
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class _0191e_NumberOfOneBits {

    @Test
    void test01() {
        int r = slt(11);
        Assertions.assertEquals(3, r);
    }

    @Test
    void test02() {
        int r = slt(128);
        Assertions.assertEquals(1, r);
    }

    @Test
    void test03() {
        int r = slt(2147483645);
        Assertions.assertEquals(30, r);
    }

    // 同 0190
    int slt(int a) {
        int r = 0;
        for (int i = 0; i < 32; i++) {
            int x = a << i;
            if (x < 0) {
                r++;
            } else if (x == 0) {
                break;// 稍微优化
            }
        }
        return r;
    }

    /**
     * 更优的，知名算法：Brian Kernighan 算法
     *
     * 示例 1：n = 11（二进制 1011）
     * 初始：n = 11（1011），count = 0。
     *
     * 第一次循环：
     * n & (n - 1) = 1011 & 1010 = 1010（10）。
     * n = 10，count = 1。
     * 第二次循环：
     * n & (n - 1) = 1010 & 1001 = 1000（8）。
     * n = 8，count = 2。
     * 第三次循环：
     * n & (n - 1) = 1000 & 0111 = 0000（0）。
     * n = 0，count = 3。
     * 循环结束，返回 count = 3。
     *
     *
     * 示例 2：n = 128（二进制 10000000）
     * 初始：n = 128（10000000），count = 0。
     *
     * 第一次循环：
     * n & (n - 1) = 10000000 & 01111111 = 00000000（0）。
     * n = 0，count = 1。
     * 循环结束，返回 count = 1。
     */
    int slt2(int a) {
        int count = 0;
        while (a != 0) {
            a &= (a - 1); // 消除最低位的1
            count++;
        }
        return count;
    }

    // 题目中提出，对于频繁调用，怎么优化？
    // 这里是经典的优化法：查表法
    // 就是将[0,255]这里的数先预计算、预存起来，下面的 table，其下标 i 对应的元素值，就是 i 这个数的二进制包含几个1.
    static int[] table = new int[256];
    static {
        for (int i = 0; i < 256; i++) {
            table[i] = (i & 1) + table[i >> 1]; // 这里也挺巧妙
        }
    }
    int slt3(int a){
        int count = 0;
        // 每次处理 8 位
        while (a != 0) {
            count += table[a & 0xFF]; // 取最低 8 位
            /*
            注：无符号右移，就是连同符号位一起右移，如果是负数（符号位1），无符号右移后，会变成整数，因为高位会被0填充
             */
            a >>>= 8; // 无符号右移 8 位
        }
        return count;
    }
}
