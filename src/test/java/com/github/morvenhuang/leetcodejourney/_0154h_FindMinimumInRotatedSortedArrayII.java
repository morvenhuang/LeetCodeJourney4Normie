package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 * <p>
 * Example 1:
 * Input: [1,3,5]
 * Output: 1
 * <p>
 * Example 2:
 * Input: [2,2,2,0,1]
 * Output: 0
 * <p>
 * Note:
 * This is a follow up problem to  Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 */
public class _0154h_FindMinimumInRotatedSortedArrayII {

    @Test
    void test01() {
        int[] a = {2, 2, 2, 0, 1};
        int r = slt(a);
        Assertions.assertEquals(0, r);
    }

    @Test
    void test02() {
        int[] a = {2, 2, 2, 2, 2};
        int r = slt(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    void test03() {
        int[] a = {2, 2, 2, 2, 2, 0, 1};
        int r = slt(a);
        Assertions.assertEquals(0, r);
    }

    @Test
    void test04() {
        int[] a = {2, 2, 2, 0, 1, 1, 1, 1, 1, 1};
        int r = slt(a);
        Assertions.assertEquals(0, r);
    }

    @Test
    void test05() {
        int[] a = {2, 2, 2, 0};
        int r = slt(a);
        Assertions.assertEquals(0, r);
    }

    // 参见 0153，没什么难度
    int slt(int[] a) {
        int head = 0;
        int tail = a.length - 1;
        while (head <= tail) {
            if (a[head] <= a[tail]) {
                return a[head];
            }
            int mid = head + (tail - head + 1) / 2;
            if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
                return a[mid];
            }
            if (a[head] > a[mid]) { // 非递增区间
                tail = mid - 1;
            } else if (a[mid] > a[tail]) { // 非递增区间
                head = mid + 1;
            }
        }
        throw new RuntimeException("Oops");
    }
}
