package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it
 * would be if it were inserted in order. You may assume no duplicates in the array.
 * <p>
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 * <p>
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 * <p>
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 * <p>
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class _0035e_SearchInsertPosition {

    @Test
    public void test01() {
        int[] a = {1, 3, 5, 6};
        int ret = slt(a, 2);
        Assertions.assertEquals(1, ret);
    }

    @Test
    public void test02() {
        int[] a = {1, 3, 5, 6};
        int ret = slt(a, 5);
        Assertions.assertEquals(2, ret);
    }

    @Test
    public void test03() {
        int[] a = {1, 3, 5, 6};
        int ret = slt(a, 7);
        Assertions.assertEquals(4, ret);
    }

    @Test
    public void test04() {
        int[] a = {1, 3, 5, 6};
        int ret = slt(a, 0);
        Assertions.assertEquals(0, ret);
    }

    // 二分法，没什么可说的
    int slt(int[] a, int val) {
        int head = 0;
        int tail = a.length - 1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (a[mid] == val) {
                return mid;
            }
            if (val < a[mid]) { // 目标位置在左半边
                if (mid == 0 || val > a[mid - 1]) {
                    return mid;
                }
                tail = mid - 1;
            } else { // 目标位置在右半边
                if (mid == a.length - 1 || val < a[mid + 1]) {
                    return mid + 1;
                }
                head = mid + 1;
            }
        }
        return -1;
    }
}
