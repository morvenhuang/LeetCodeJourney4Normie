package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array. <br>
 * <br>
 * Note: <br>
 * The number of elements initialized in nums1 and nums2 are m and n respectively. <br>
 * You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2. <br>
 * <br>
 * Example: <br>
 * Input: <br>
 * nums1 = [1,2,3,0,0,0], m = 3 <br>
 * nums2 = [2,5,6],       n = 3 <br>
 * Output: [1,2,2,3,5,6] <br>
 * <br>
 * Constraints: <br>
 * -10^9 <= nums1[i], nums2[i] <= 10^9 <br>
 * nums1.length == m + n <br>
 * nums2.length == n <br>
 */
public class _0088e_MergeSortedArray {

    @Test
    public void test01() {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};
        slt(a, b);
        int[] expected = {1, 2, 2, 3, 5, 6};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, a, false));
    }

    @Test
    public void test02() {
        int[] a = {2, 5, 6, 0, 0, 0};
        int[] b = {2, 5, 6};
        slt(a, b);
        int[] expected = {2, 2, 5, 5, 6, 6};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, a, false));
    }

    @Test
    public void test03() {
        int[] a = {2, 5, 6, 0, 0, 0};
        int[] b = {1, 2, 3};
        slt(a, b);
        int[] expected = {1, 2, 2, 3, 5, 6};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, a, false));
    }

    void slt(int[] a, int[] b) {
        int i = a.length - b.length - 1; // 用在数组 a 的游标
        int j = b.length - 1; // 用在数组 b 的游标
        int index = a.length - 1;
        while (i >= 0 && j >= 0) { // 从各自的尾部开始，依次找到最大值、次大值、……
            if (a[i] > b[j]) {
                a[index] = a[i];
                i--;
            } else {
                a[index] = b[j];
                j--;
            }
            index--;
        }

        while (j >= 0) {
            a[index] = b[j];
            j--;
            index--;
        }
    }
}
