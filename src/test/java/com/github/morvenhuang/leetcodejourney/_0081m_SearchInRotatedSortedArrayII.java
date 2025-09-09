package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.<br>
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).<br>
 * You are given a target value to search. If found in the array return true, otherwise return false.<br>
 * <br>
 * Example 1:<br>
 * Input: nums = [2,5,6,0,0,1,2], target = 0<br>
 * Output: true<br>
 * <br>
 * Example 2:<br>
 * Input: nums = [2,5,6,0,0,1,2], target = 3<br>
 * Output: false<br>
 * <br>
 * Follow up:<br>
 * This is a follow up problem to  Search in Rotated Sorted Array, where nums may contain duplicates.<br>
 * Would this affect the run-time complexity? How and why?<br>
 */
public class _0081m_SearchInRotatedSortedArrayII {

    @Test
    public void test01() {
        int[] a = {2, 5, 6, 0, 0, 1, 2};
        boolean r = slt(a, 0);
        Assertions.assertTrue(r);
    }

    @Test
    public void test02() {
        int[] a = {2, 5, 6, 0, 0, 1, 2};
        boolean r = slt(a, 3);
        Assertions.assertTrue(!r);
    }

    /**
     * 题目意思是一个有序数组，分成两截，然后这两截交换位置，这两截自身仍然是有序的，还有一个特点是后一截的最大值，比前一截的最小值还小。
     * 如何要在这样的数组中找一个数。
     * 从一个有序数组中找一个数，当然必须想到二分法，所以这里就是要看看能不能使用二分法。
     */
    boolean slt(int[] a, int target) {
        int head = 0;
        int tail = a.length - 1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2; // 标准二分法写法
            if (a[mid] == target) {
                return true;
            }

            // 如果中间位置的值>=头部的值，那么这个中间位置不可能位于后半截，因为后半截的所有数都小于前半截的最小值，
            // 也就是，此时 mid 位于前半截，这样的话，左侧肯定是递增的。
            if (a[head] <= a[mid]) { // 左侧递增，右侧非递增
                if (a[head] <= target && target < a[mid]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else if (a[tail] >= a[mid]) { // 右侧递增，左侧非递增
                if (a[mid] < target && target <= a[tail]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return false;
    }
}
