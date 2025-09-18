package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1]
 * and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * Your solution must use only constant extra space.
 * <p>
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * <p>
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * <p>
 * Example 3:
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 * <p>
 * Constraints:
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 */
public class _0167m_TwoSum2InputArrayIsSorted {

    @Test
    void test01() {
        int[] a = {2, 7, 11, 15};
        int[] r = slt(a, 9);
        int[] expected = {1, 2};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    @Test
    void test02() {
        int[] a = {2, 3, 4};
        int[] r = slt(a, 6);
        int[] expected = {1, 3};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    @Test
    void test03() {
        int[] a = {-1, 0};
        int[] r = slt(a, -1);
        int[] expected = {1, 2};
        Assertions.assertTrue(TestHelper.same1DArrays(expected, r, false));
    }

    // 这个双指针就行了吧？
    int[] slt(int[] a, int target) {
        int[] r = new int[2];

        int head = 0;
        int tail = a.length - 1;
        while (head < tail) {
            if (a[head] + a[tail] == target) {
                r[0] = head + 1;
                r[1] = tail + 1;
                return r;
            }
            if (a[head] + a[tail] > target) {
                tail--;
            } else {
                head++;
            }
        }
        throw new RuntimeException("Oops");
    }
}
