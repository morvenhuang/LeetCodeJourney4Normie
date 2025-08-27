package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * （附带的要求是要在原数组内进行操作，而不允许另行使用新数组）
 * <p>
 * Example 1:
 * Given nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * It doesn't matter what values are set beyond the returned length.
 */
public class _0026e_RemoveDuplicatesFromSortedArray {

    @Test
    public void test01() {
        int[] a = {1, 1, 2};
        int ret = slt(a);
        Assertions.assertEquals(2, ret);
        Assertions.assertTrue(check(new int[]{1, 2}, a));
    }

    @Test
    public void test02() {
        int[] a = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int ret = slt(a);
        Assertions.assertEquals(5, ret);
        Assertions.assertTrue(check(new int[]{0, 1, 2, 3, 4}, a));
    }

    boolean check(int[] expected, int[] actual) {
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 对于已排序的数组，这就简单多了，遇到不同的就往前填充，具体填充到哪个位置，用一个 index 变量来记录
     */
    int slt(int[] a) {
        int index = 1; // 标识目前非重复的元素已经填充到数组的哪个位置
        for (int i = 1; i < a.length; i++) {
            // 如当前值与前一个元素相同，跳过
            if (a[i] == a[i - 1]) {
                continue;
            }
            // 如当前值与前一个元素不同，则往前填充
            if (i != index) {
                a[index] = a[i];
                index++;
            }
        }
        return index;
    }
}
