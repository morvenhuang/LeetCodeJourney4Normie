package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class _0057m_InsertInterval {

    @Test
    public void test01() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        List<List<Integer>> r = slt(intervals, newInterval);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 5));
        expected.add(Arrays.asList(6, 9));
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, false, false));
    }

    @Test
    public void test02() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        List<List<Integer>> r = slt(intervals, newInterval);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1, 2));
        expected.add(Arrays.asList(3, 10));
        expected.add(Arrays.asList(12, 16));
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, false, false));
    }

    // 这个比 0056 更简单，都排好序了
    List<List<Integer>> slt(int[][] a, int[] insert) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < a.length; i++) {
            if (flag) {
                ret.add(Arrays.asList(a[i][0], a[i][1]));
                continue;
            }
            if (insert[1] < a[i][0]) { // 无交集，insert 在前
                ret.add(Arrays.asList(insert[0], insert[1]));
                flag = true;
                if (i == a.length - 1) {
                    ret.add(Arrays.asList(a[i][0], a[i][1]));
                }
            } else if (insert[0] > a[i][1]) { // 无交集，insert 在后
                ret.add(Arrays.asList(a[i][0], a[i][1]));
                if (i == a.length - 1) {
                    ret.add(Arrays.asList(insert[0], insert[1]));
                }
            } else {
                int l = Math.min(insert[0], a[i][0]);
                int r = Math.max(insert[1], a[i][1]);
                insert = new int[]{l, r};
            }
        }
        return ret;
    }
}
