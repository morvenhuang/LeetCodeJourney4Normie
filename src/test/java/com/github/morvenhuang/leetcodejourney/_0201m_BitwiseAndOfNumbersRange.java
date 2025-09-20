package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in
 * this range, inclusive.
 * <p>
 * Example 1:
 * Input: left = 5, right = 7
 * Output: 4
 * <p>
 * Example 2:
 * Input: left = 0, right = 0
 * Output: 0
 * <p>
 * Example 3:
 * Input: left = 1, right = 2147483647
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * 0 <= left <= right <= 2^31 - 1
 */
public class _0201m_BitwiseAndOfNumbersRange {

    @Test
    void test01(){
        int r = slt(5,7);
        Assertions.assertEquals(4,r);
    }

    /**
     * 这个没有思路。
     *
     * 这取决于能不能找到规律，规律是：经过所有的位与运算，只有 left 和 right 的二进制表示的公共前缀位保留下来
     */
    int slt(int left, int right) {
        int shift = 0;
        // 右移 left 和 right 直到它们相等
        while (left != right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        // 左移恢复公共前缀
        return left << shift;
    }
}
