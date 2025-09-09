package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram’s bar height where the width of each bar is 1, find the <br>
 * area of largest rectangle in the histogram. <br>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3]. <br>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit. <br>
 * <br>
 * Example: <br>
 * Input: [2,1,5,6,2,3] <br>
 * Output: 10 <br>
 */
public class _0084h_LargestRectangleInHistogram {

    @Test
    public void test01() {
        int[] a = {2, 1, 5, 6, 2, 3};
        int r = slt3(a);
        Assertions.assertEquals(10, r);
    }

    @Test
    public void test02() {
        int[] a = {2, 3, 4, 2, 0, 1, 5};
        int r = slt3(a);
        Assertions.assertEquals(8, r);
    }

    @Test
    public void test03() {
        int[] a = {2, 2, 2, 2};
        int r = slt3(a);
        Assertions.assertEquals(8, r);
    }

    @Test
    public void test04() {
        int[] a = {5, 4, 3, 2};
        int r = slt3(a);
        Assertions.assertEquals(9, r);
    }

    @Test
    public void test05() {
        int[] a = {3, 2, 1, 2, 3};
        int r = slt3(a);
        Assertions.assertEquals(5, r);
    }

    @Test
    public void test06() {
        int[] a = {1, 2, 1};
        int r = slt3(a);
        Assertions.assertEquals(3, r);
    }

    /**
     * 贪心？尝试以i为长方形右侧，求局部最优，最后得到全局最优（不过，这个局部最优到全局最优的过程有依据吗？）
     * 以下解法可以通过除了 test06 之外的测试，而 test06 不通过，原因在于 area1 >= area2 这个判断，实际上对于 area1 == area2 需要特殊处理，保存两种情况。
     * 但是如果这样，后续保存的内容会越来越多，因此，这个算法无法正常工作。
     */
    int slt(int[] a) {
        int max = a[0];
        int curr = a[0];
        int w = 1;
        int h = a[0];
        for (int i = 1; i < a.length; i++) {
            int area1 = a[i]; // 自己独自
            int area2 = Math.min(h, a[i]) * (w + 1); // 与 a[i-1] 为右边界的最大值进行"合并"
            if (area1 >= area2) {
                w = 1;
                h = a[i];
                curr = area1;
            } else {
                w = w + 1;
                h = Math.min(h, a[i]);
                curr = area2;
            }
            max = Math.max(max, curr);

        }
        return max;
    }

    /**
     * 既然上面通过确定矩形右侧的方式来取得局部最优，这种方法行不通。
     * 那么，考虑通过确定矩形的高来取得局部最优。
     * 如果我们以 a[i] 为高，高度固定了，那么其最大面积取决于它的宽度，也就是它向左右两边能拓展到什么程度？
     * 一直拓展，直到遇到比它矮的之前。
     */
    int slt2(int[] a) {
        int max = -1;
        for (int i = 0; i < a.length; i++) {
            int l = getLeft(a, i);
            int r = getRight(a, i);
            int w = r - l + 1;
            max = Math.max(w * a[i], max);
        }
        return max;
    }

    /**
     * slt2 中的很多遍历是重复的，跟使用2层嵌套 for 循环的暴力算法也差不多。
     * 标准的解法是用：单调栈（Monotonic Stack）
     * <p>
     * [2,1,5,6,2,3]为例：
     * 初始：maxArea = 0, stack = []
     * 遍历索引 i从 0 到 5：
     * <p>
     * 1. i=0 (h=2)
     * 栈空，直接入栈。stack = [0](对应高度 [2])
     * <p>
     * 2. i=1 (h=1)
     * 当前高度 1< 栈顶高度 2，触发弹出和计算（局部决策）
     * 弹出 index=0, h=2。
     * 栈现在为空，所以左边界是 0（如果栈空，左边界就是起点）。
     * 宽度 = i(1) - 0 = 1
     * 面积 = 2 * 1 = 2
     * maxArea = max(0, 2) = 2
     * 当前 i=1入栈。stack = [1](高度 1)
     * <p>
     * 3. i=2 (h=5)
     * 当前高度 5> 栈顶高度 1，直接入栈。stack = [1, 2](高度 1,5)
     * <p>
     * 4. i=3 (h=6)
     * 当前高度 6> 栈顶高度 5， 直接入栈。stack = [1, 2, 3](高度 1,5,6)
     * <p>
     * 5. i=4 (h=2)
     * 当前高度 2< 栈顶高度 6， 触发弹出和计算
     * 弹出 index=3, h=6。
     * 栈顶现在是 index=2(h=5)，所以左边界是 2+1=3。
     * 宽度 = i(4) - 3 = 1
     * 面积 = 6 * 1 = 6
     * maxArea = max(2, 6) = 6
     * <p>
     * 当前高度 2< 新栈顶高度 5， 再次触发弹出和计算
     * 弹出 index=2, h=5。
     * 栈顶现在是 index=1(h=1)，所以左边界是 1+1=2。
     * 宽度 = i(4) - 2 = 2
     * 面积 = 5 * 2 = 10
     * maxArea = max(6, 10) = 10(找到全局最优)
     * <p>
     * 当前高度 2> 新栈顶高度 1-> 入栈。stack = [1, 4](高度 1,2)
     * <p>
     * 6. i=5 (h=3)
     * 当前高度 3> 栈顶高度 2-> 直接入栈。stack = [1, 4, 5](高度 1,2,3)
     * <p>
     * 7. 遍历结束，清理栈
     * 右边界固定为 n=6。
     * 弹出 i=5 (h=3):
     * 左边界 = 4 (栈顶index) + 1 = 5
     * 宽度 = 6 - 5 = 1
     * 面积 = 3 * 1 = 3, maxArea不变。
     * <p>
     * 弹出 i=4 (h=2):
     * 左边界 = 1 (栈顶index) + 1 = 2
     * 宽度 = 6 - 2 = 4
     * 面积 = 2 * 4 = 8, maxArea不变。
     * <p>
     * 弹出 i=1 (h=1):
     * 栈空，左边界为 0。
     * 宽度 = 6 - 0 = 6
     * 面积 = 1 * 6 = 6, maxArea不变。
     * <p>
     * 最终结果：maxArea = 10
     */
    int slt3(int[] a) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < a.length; i++) {
            while (!stack.isEmpty() && a[i] < a[stack.peek()]) {
                int r = i - 1; // 意味着找到了以 stack.peek() 为 height 的长方形的最大右边界
                int h = a[stack.pop()];
                int l = stack.isEmpty() ? 0 : stack.peek() + 1; // 由于这是一个递增栈，以 stack.peek() 为 height 的长方形的最大左边界就是现在的栈顶+1
                max = Math.max(max, h * (r - l + 1));
            }
            stack.push(i);
        }

        // 栈中可能会有剩余，例如原始数组本身就是递增的，或者原始数组后半截是递增。
        while (!stack.isEmpty()) {
            int r = a.length - 1; // 固定的右边界
            int h = a[stack.pop()];
            int l = stack.isEmpty() ? 0 : stack.peek() + 1;
            max = Math.max(max, h * (r - l + 1));
        }

        return max;
    }

    int getLeft(int[] a, int i) {
        int m = i - 1;
        while (m > -1) {
            if (a[m] < a[i]) {
                break;
            }
            m--;
        }
        return m + 1;
    }

    int getRight(int[] a, int i) {
        int m = i + 1;
        while (m < a.length) {
            if (a[m] < a[i]) {
                break;
            }
            m++;
        }
        return m - 1;
    }

}
