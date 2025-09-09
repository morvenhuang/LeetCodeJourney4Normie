package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class _0120m_Triangle {

    @Test
    void test01(){
        List<List<Integer>> a = new ArrayList<>();
        a.add(Arrays.asList(2));
        a.add(Arrays.asList(3,4));
        a.add(Arrays.asList(6,5,7));
        a.add(Arrays.asList(4,1,8,3));

        int r = slt(a);
        Assertions.assertEquals(11, r);
    }

    /**
     * 将上述三角形数组左对齐后，可以看到某一行的某个位置，可以从它的左上方走来，也可以从它的正上方走来。
     * 这个和走二维矩阵迷宫一样，很容易想到用 dp。
     * 这个应该是一个典型的、简单的 dp，重复用一个长度为 n 的数组来存储状态，其中元素 i 表示，到达某一行的地i个元素的最优值。
     */
    int slt(List<List<Integer>> a) {
        int[] state = new int[a.size()];
        state[0] = a.get(0).get(0);

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < a.size(); i++) {
            // 从尾部开始遍历，可以避免保存临时变量
            for (int j = a.get(i).size() - 1; j >= 0; j--) {
                if (a.get(i - 1).size() > j && j - 1 >= 0) {
                    state[j] = Math.min(state[j], state[j - 1]) + a.get(i).get(j);
                } else if (a.get(i - 1).size() > j) { // 只有正上方有东西
                    state[j] = state[j] + a.get(i).get(j);
                } else if (j - 1 >= 0) { // 只有左上方有东西
                    state[j] = state[j - 1] + a.get(i).get(j);
                }
                if (i == a.size() - 1) { // 到达最后一行时，比较分别以 4,1,8,3 为路径终点的这四种情况，取出最大值，就是最终结果。
                    min = Math.min(min, state[j]);
                }
            }
        }

        return min;
    }
}
