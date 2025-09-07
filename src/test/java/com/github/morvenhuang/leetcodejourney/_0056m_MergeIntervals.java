package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class _0056m_MergeIntervals {

    @Test
    public void test01() {
        int[][] a = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        List<Tuple> r = slt(a);
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        Assertions.assertTrue(check(expected, r));
    }

    @Test
    public void test02() {
        int[][] a = {{1, 4}, {4, 5}};
        List<Tuple> r = slt(a);
        int[][] expected = {{1, 5}};
        Assertions.assertTrue(check(expected, r));
    }

    @Test
    public void test03() {
        int[][] a = {{8, 10}, {15, 18}, {1, 3}, {2, 6}};
        List<Tuple> r = slt(a);
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        Assertions.assertTrue(check(expected, r));
    }

    boolean check(int[][] expected, List<Tuple> r) {
        if (expected.length != r.size()) {
            return false;
        }
        for (int i = 0; i < expected.length; i++) {
            if (expected[i][0] != r.get(i).l || expected[i][1] != r.get(i).r) {
                return false;
            }
        }
        return true;
    }

    // 这是一个简单题，很自然想到先按区间起点进行排序，这样就很好处理了
    // 整体时间复杂度 O(n log n)，这个主要是排序导致
    List<Tuple> slt(int[][] a) {
        List<Tuple> origin = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            origin.add(new Tuple(a[i][0], a[i][1]));
        }
        Collections.sort(origin);

        List<Tuple> list = new ArrayList<>();
        Tuple tuple = origin.get(0);

        for (int i = 1; i < origin.size(); i++) {
            if (tuple.r >= origin.get(i).l) { // 有交集
                tuple.r = Math.max(tuple.r, origin.get(i).r);
            } else {
                list.add(new Tuple(tuple.l, tuple.r));
                tuple = origin.get(i);
            }
        }
        list.add(tuple);
        return list;
    }

    static class Tuple implements Comparable {
        int l;
        int r;

        public Tuple(int lx, int rx) {
            l = lx;
            r = rx;
        }

        @Override
        public int compareTo(Object o) {
            Tuple other = (Tuple) o;
            return Integer.compare(l, other.l);
        }
    }
}
