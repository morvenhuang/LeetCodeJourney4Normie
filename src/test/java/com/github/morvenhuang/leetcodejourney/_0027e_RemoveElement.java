package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * The order of elements can be changed. It doesn’t matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * Given nums = [3,2,2,3], val = 3,
 * Your function should return length = 2, with the first two elements of nums being 2.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * Note that the order of those five elements can be arbitrary.
 * It doesn't matter what values are set beyond the returned length.
 */
public class _0027e_RemoveElement {

    @Test
    public void test01() {
        int[] a = {0, 1, 2, 2, 3, 0, 4, 2};
        int r = slt(a, 2);
        Assertions.assertEquals(5, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{0, 1, 3, 0, 4}, Arrays.copyOf(a, r), true));
    }

    @Test
    public void testRemoveElement_StandardCase() {
        int[] a = {3, 2, 2, 3};
        int val = 3;
        int r = slt(a, val);
        Assertions.assertEquals(2, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{2, 2}, Arrays.copyOf(a, r), true));
    }

    @Test
    public void testRemoveElement_NoMatchingElements() {
        int[] a = {1, 2, 3, 4, 5};
        int val = 6;
        int r = slt(a, val);
        Assertions.assertEquals(5, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{1, 2, 3, 4, 5}, Arrays.copyOf(a, r), true));
    }

    @Test
    public void testRemoveElement_AllElementsMatch() {
        int[] a = {4, 4, 4, 4};
        int val = 4;
        int r = slt(a, val);
        Assertions.assertEquals(0, r);
    }

    @Test
    public void test02() {
        int[] a = {2, 4, 4, 4, 4};
        int val = 4;
        int r = slt(a, val);
        Assertions.assertEquals(1, r);
        Assertions.assertTrue(TestHelper.same1DArrays(new int[]{2}, Arrays.copyOf(a, r), false));
    }

    /**
     * 核心思路就是把 val 扔到最尾部（交换），用头尾两个指针
     */
    int slt(int[] a, int val) {
        int head = 0;
        int tail = a.length - 1;

        while (head < tail) {
            if (a[head] == val) { // head 需要被移走
                if (a[tail] != val) { // 如果尾部不是目标值，这交换这两个数
                    a[head] = a[tail];
                    a[tail] = val;
                    head++;
                }
                tail--; // 尾部指针前移
            } else {
                head++; // 头部非目标值，无需操作，指针后移
            }
        }
        // 这个地方有个边界条件要处理一下，上面循环结束时，说明 head 与 tail 指针已经相遇，而相遇那个位置的元素还没有确定到底是什么
        return a[head] == val ? head : head + 1;
    }
}
