package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target
 * value. Your algorithm’s runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class _0034m_FindFirstAndLastPositionOfElementInSortedArray {

    @Test
    public void test01() {
        int[] a = {5, 7, 7, 8, 8, 10};
        List<Integer> ret = slt(a, 8);
        List<Integer> expected = Arrays.asList(3, 4);
        Assertions.assertTrue(expected.equals(ret));
    }

    @Test
    public void test02() {
        int[] a = {5, 7, 7, 8, 8, 10};
        List<Integer> ret = slt(a, 6);
        List<Integer> expected = Arrays.asList(-1, -1);
        Assertions.assertTrue(expected.equals(ret));
    }

    @Test
    public void test03() {
        int[] a = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        List<Integer> ret = slt(a, 1);
        List<Integer> expected = Arrays.asList(0, 8);
        Assertions.assertTrue(expected.equals(ret));
    }

    /**
     * 一看到排序数组和 O(log n)，就会想到二分查找，只不过这里需要分别查找左右边界。
     */
    List<Integer> slt(int[] a, int target) {
        int head = 0;
        int tail = a.length - 1;
        int pos1 = -1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (a[mid] == target) {
                if (mid == head) { // 特殊处理
                    pos1 = head;
                    break;
                } else {
                    if (a[mid - 1] != target) { // 找到左边界
                        pos1 = mid;
                        break;
                    } else {
                        tail = mid - 1;
                    }
                }
            } else if (a[mid] > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        head = 0;
        tail = a.length - 1;
        int pos2 = -1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (a[mid] == target) {
                if (mid == tail) { // 特殊处理
                    pos2 = tail;
                    break;
                } else {
                    if (a[mid + 1] != target) { // 找到右边界
                        pos2 = mid;
                        break;
                    } else {
                        head = mid + 1;
                    }
                }
            } else if (a[mid] > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        if ((pos1 >= 0 && pos2 == -1) || (pos1 == -1 && pos2 >= 0)) {
            if (pos1 >= 0) {
                return Arrays.asList(pos1, pos1);
            } else {
                return Arrays.asList(pos2, pos2);
            }
        } else {
            return Arrays.asList(pos1, pos2);
        }
    }


}
