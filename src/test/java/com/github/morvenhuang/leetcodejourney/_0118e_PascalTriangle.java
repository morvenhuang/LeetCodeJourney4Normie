package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal’s triangle. <br>
 * Note: In Pascal’s triangle, each number is the sum of the two numbers directly above it. <br>
 * <br>
 * Example: <br>
 * Input: 5 <br>
 * Output: <br>
 * [ <br>
 * [1], <br>
 * [1,1], <br>
 * [1,2,1], <br>
 * [1,3,3,1], <br>
 * [1,4,6,4,1] <br>
 * ] <br>
 */
public class _0118e_PascalTriangle {

    @Test
    public void test01() {
        List<List<Integer>> r = slt(5);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1));
        expected.add(Arrays.asList(1, 1));
        expected.add(Arrays.asList(1, 2, 1));
        expected.add(Arrays.asList(1, 3, 3, 1));
        expected.add(Arrays.asList(1, 4, 6, 4, 1));
        Assertions.assertTrue(TestHelper.same2DLists(expected, r, false, false));
    }

    // 实际上，如果像上面一样，对数组进行左对其，可以看到，每一个元素，是（其正上方+其左上方）
    List<List<Integer>> slt(int a) {

        List<List<Integer>> r = new ArrayList<>();
        r.add(Arrays.asList(1));

        for (int i = 1; i < a; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) { // 每一行有多少个元素
                // 正上方有值 && 左上方有值
                if (r.get(i - 1).size() > j && j - 1 >= 0) {
                    list.add(r.get(i - 1).get(j) + r.get(i - 1).get(j - 1));
                } else if (r.get(i - 1).size() > j) {
                    list.add(r.get(i - 1).get(j));
                } else if (j - 1 >= 0) {
                    list.add(r.get(i - 1).get(j - 1));
                }
            }
            r.add(list);
        }

        return r;
    }
}
