package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * <p>
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * <p>
 * Note:
 * Your solution should be in logarithmic complexity.
 */
public class _0162m_FindPeakElement {

    @Test
    void test01() {
        int[] a = {1, 2, 3, 1};
        int r = slt(a);
        Assertions.assertEquals(2, r);
    }

    @Test
    void test02() {
        int[] a = {1, 2, 1, 3, 5, 6, 4};
        int r = slt(a);
        List<Integer> expected = Arrays.asList(1, 5);
        Assertions.assertTrue(expected.contains(r));
    }

    /**
     * 要求 O(log n)，一般只有二分法才能达到此时间复杂度。
     * 没有思路，看了网上的解法，就是二分，其实这里面容易掉入一个思维陷阱：一个没有排序的数组，怎么能用二分？
     * 其实二分并不一定要排序，前面做过的几个轴对称数组的例子，就不是（完全）排序的数组。
     * 这里可以这样想，如果 a[mid]>a[mid+1]，
     * 那么，左侧（含mid）肯定有 peak，不管左侧是递减（则第一个元素就是 peak）、递增、还是起伏，会有 peak。
     * 右侧（不含mid）不一定有 peak，例如，如果右侧是一个递减序列，则就没有 peak。
     */
    int slt(int[] a) {
        int head = 0;
        int tail = a.length - 1;
        while (head < tail) {
            /*
            mid = head + (tail - head + 1) / 2 VS. mid = head + (tail - head) / 2
            标准写法是后者。原因是这样可以避免死循环：
            当区间长度为偶数时（如 head=2，tail=3），(tail - head) / 2 会向下取整，得到中间靠左的位置（mid=2）。
            此时若目标值在右侧，head 会更新为 mid+1，从而缩小区间到 [3,3]，确保循环终止。若使用 (tail - head + 1) / 2，
            则 mid=3，若目标值在左侧且逻辑为 tail = mid，区间会保持 [2,3]，导致死循环。
             */
            int mid = head + (tail - head) / 2;
            if (a[mid] > a[mid + 1]) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        return head; // 找个具体的例子，比较好想出到底是返回 head 还是 tail
    }
}
