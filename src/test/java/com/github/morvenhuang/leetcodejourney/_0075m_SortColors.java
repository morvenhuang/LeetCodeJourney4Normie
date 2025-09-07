package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are
 * adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library’s sort function for this problem.
 * <p>
 * Example 1:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0’s, 1’s, and 2’s, then overwrite array with total number of 0’s, then 1’s and followed by 2’s.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class _0075m_SortColors {

    @Test
    public void test01() {
        int[] a = {2, 0, 2, 1, 1, 0};
        slt(a);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{0, 0, 1, 1, 2, 2}, a, false));
    }

    @Test
    public void test02() {
        int[] a = {1, 0, 2, 1, 1, 0};
        slt(a);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{0, 0, 1, 1, 1, 2}, a, false));
    }

    @Test
    public void test03() {
        int[] a = {1, 0, 2, 1, 1, 0, 0, 1, 0, 2, 0};
        slt(a);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2}, a, false));
    }

    /**
     * 用两次循环的方法，上面题目中已经给出了。
     * 题目要求用一次循环。
     * <p>
     * 其实想法也简单，遍历数组，将0扔到头部，将2扔到尾部，记录一下0占用了多少，2占用了多少，如果中间还有间隔，就填上1。
     * 不过，这种写法过于啰嗦了。
     */
    void slt(int[] a) {
        int head = 0;
        int tail = a.length - 1;
        int zeroCnt = 0;
        int twoCnt = 0;

        while (head <= tail) {
            if (a[head] < 2) {
                if (a[head] == 0) {
                    a[zeroCnt] = 0;
                    if (zeroCnt != head) {
                        a[head] = 1;
                    }
                    zeroCnt++;
                }
                head++;
                continue;
            }

            if (a[tail] > 0) {
                if (a[tail] == 2) {
                    a[a.length - 1 - twoCnt] = 2;
                    if (tail != a.length - 1 - twoCnt) {
                        a[tail] = 1;
                    }
                    twoCnt++;
                }
                tail--;
                continue;
            }

            a[zeroCnt] = 0;
            if (zeroCnt != head) {
                a[head] = 1;
            }
            zeroCnt++;
            a[a.length - 1 - twoCnt] = 2;
            if (a.length - 1 - twoCnt != tail) {
                a[tail] = 1;
            }
            twoCnt++;
            head++;
            tail--;
        }
    }

    /**
     * 这是网上比较简洁的写法，整体思想上与上面解法一样，当上面我的写法用两个指针+两个计数，是没有必要的，一个指针+两个计数，就够了。
     * （这个题有一个专门的名称："荷兰国旗问题"，slt2 也是其标准解法。）
     */
    void slt2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int i = 0;

        while (i <= high) {
            switch (nums[i]) {
                case 0:
                    // Swap with low pointer
                    Utils.swap(nums, low, i);
                    low++;
                    i++;
                    break;
                case 1:
                    // Just move mid pointer
                    i++;
                    break;
                case 2:
                    // Swap with high pointer
                    Utils.swap(nums, i, high);
                    high--;
                    break;
            }
        }
    }
}
