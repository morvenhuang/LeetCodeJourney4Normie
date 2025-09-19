package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Reverse bits of a given 32 bits signed integer.
 *
 * Example 1:
 * Input: n = 43261596
 * Output: 964176192
 * Explanation:
 * Integer	Binary
 * 43261596	00000010100101000001111010011100
 * 964176192	00111001011110000010100101000000
 *
 * Example 2:
 * Input: n = 2147483644
 * Output: 1073741822
 * Explanation:
 * Integer	Binary
 * 2147483644	01111111111111111111111111111100
 * 1073741822	00111111111111111111111111111110
 *
 * Constraints:
 * 0 <= n <= 231 - 2
 * n is even.
 *
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class _0190e_ReverseBits {

    @Test
    void test01() {
        int r = slt(43261596);
        Assertions.assertEquals(964176192,r);
    }

    @Test
    void test02() {
        int r = slt(2147483644);
        Assertions.assertEquals(1073741822,r);
    }

    /**
     * 这个没什么可想的，要么就是左移，然后用符号为判断（也可用位与操作）；要么是右移，用位与操作判断。
     */
    int slt(int a) {
        if (a == 0) {
            return 0;
        }
        int r = 0;
        for (int i = 0; i < 32; i++) {
            if (a << i >= 0) { // 通过有符号数的最高位是符号位，来判断此时的最高位是0或1
                //
            } else{ // 负数，说明此时高位是1
                r += 1<<i;
            }
        }
        return r;
    }
}
