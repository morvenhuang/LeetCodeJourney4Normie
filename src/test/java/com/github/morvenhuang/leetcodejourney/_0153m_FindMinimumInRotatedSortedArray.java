package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * <p>
 * Example 1:
 * Input: [3,4,5,1,2]
 * Output: 1
 * <p>
 * Example 2:
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class _0153m_FindMinimumInRotatedSortedArray {

    @Test
    void test01() {
        int[] a = {3, 4, 5, 1, 2};
        int r = slt(a);
        Assertions.assertEquals(1, r);
    }

    @Test
    void test02() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        int r = slt(a);
        Assertions.assertEquals(0, r);
    }

    int slt(int[] a) {
        int head = 0;
        int tail = a.length - 1;
        while (head <= tail) {
            if (a[head] < a[tail]) {
                return a[head];
            }
            int mid = head + (tail - head + 1) / 2;
            if (a[mid] < a[mid - 1] && a[mid] > a[mid + 1]) {
                return a[mid];
            }
            if (a[head] < a[mid]) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        throw new RuntimeException("Oops");
    }
}
