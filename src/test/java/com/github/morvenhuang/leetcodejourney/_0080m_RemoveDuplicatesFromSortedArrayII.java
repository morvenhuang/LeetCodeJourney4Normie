package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.<br>
 * <p>
 * Example 1:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It doesn't matter what values are set beyond the returned length.
 * <p>
 * Clarification:
 * Confused why the returned value is an integer but your answer is an array?
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeElement(nums, val);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */
public class _0080m_RemoveDuplicatesFromSortedArrayII {

    @Test
    public void test01() {
        int[] a = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int r = slt(a);
        Assertions.assertEquals(7, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{0, 0, 1, 1, 2, 3, 3}, Arrays.copyOfRange(a, 0, r), false));
    }

    @Test
    public void test02() {
        int[] a = {1, 2, 3, 3, 3, 3, 4, 4, 4, 5};
        int r = slt(a);
        Assertions.assertEquals(7, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{1, 2, 3, 3, 4, 4, 5}, Arrays.copyOfRange(a, 0, r), false));
    }

    @Test
    public void test03() {
        int[] a = {1, 1, 1, 2, 2, 3};
        int r = slt(a);
        Assertions.assertEquals(5, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{1, 1, 2, 2, 3}, Arrays.copyOfRange(a, 0, r), false));
    }

    // 这个思路和 0026 一样，就是遍历，遇到不同的就往前填充，具体填充到哪里，使用一个标识来定位。
    // 不过这里运行重复的元素保留至多两个。
    int slt(int[] a) {
        int pos = -1;
        int currCnt = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                currCnt++;
            } else {
                currCnt = 1;
            }

            if (pos != -1) {
                if (currCnt <= 2) {
                    a[pos] = a[i];
                    pos++;
                }
            } else {
                if (currCnt == 3) {
                    pos = i;
                }
            }
        }

        return pos;
    }
}
