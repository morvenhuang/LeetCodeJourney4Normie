package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Suppose an array sorted in ascending order is rotated（旋转） at some pivot（轴） unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm’s runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class _0033m_SearchInRotatedSortedArray {

    @Test
    public void test01() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        int r = slt(a, 0);
        Assertions.assertEquals(4, r);
    }

    @Test
    public void test02() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        int r = slt(a, 3);
        Assertions.assertEquals(-1, r);
    }

    @Test
    public void test03() {
        int[] a = {9, 10, 11, 12, 0, 1, 2, 3, 4, 5, 6};
        int r = slt(a, 3);
        Assertions.assertEquals(7, r);
    }

    @Test
    public void test04() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        int r = slt(a, 4);
        Assertions.assertEquals(0, r);
    }

    @Test
    public void test05() {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        int r = slt(a, 2);
        Assertions.assertEquals(6, r);
    }

    /**
     * 这道题标记为中等难度，实际上是比较简单的，而且其要求的时间复杂度 O(log n)，也给了我们提示，最常见的时间复杂度为 O(log n) 的算法就是
     * 二分法，只不过这里的二分法，判断的条件要复杂一点。
     *
     * 注意，题目中的数组由一个有序数组旋转而得到的两个有序数组片段，所以才能这么处理。如果只是纯粹说两个有序数组拼接而成的一个数组，则下面的
     * 解法是有问题的，因为这两个数组的取值范围可能有重叠。
     */
    int slt(int[] a, int target) {
        int head = 0;
        int tail = a.length - 1;
        while (head <= tail) { // 注意二分查找的标准写法，是 <=，不是 <，否则会有边界问题。
            int mid = head + (tail - head) / 2;
            if (a[mid] == target) {
                return mid;
            }
            // 对于二分之后的左右两侧子数组，肯定其中一个子数组仍然还是递增，我们需要判断是左侧子数组，还是右侧子数组是递增。
            if (a[head] < a[mid]) { // 左侧为递增区间，右侧不是
                if (a[head] <= target && target < a[mid]) { // 因为左侧比较好判断，所以判断 target 是不是在左侧区间
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else { // 左侧为非递增区间，右侧是递增
                if (a[mid] < target && target <= a[tail]) { // 因为右侧比较好判断，所以判断 target 是不是在右侧区间
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }
        return -1;
    }
}
