package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending
 * order).
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * <p>
 * Example 3:
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * Example 4:
 * Input: nums = [1]
 * Output: [1]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100 (这个条件是不是有问题？不管了，我们还是专注核心思路)
 */
public class _0031m_NextPermutation {

    @Test
    public void test01() {
        int[] a = {1, 3, 1, 4, 3, 0};
        slt2(a);
        Assertions.assertTrue(Arrays.equals(new int[]{1, 3, 3, 0, 1, 4}, a));
    }

    @Test
    public void test02() {
        int[] a = {1, 2, 3};
        slt2(a);
        Assertions.assertTrue(Arrays.equals(new int[]{1, 3, 2}, a));
    }

    @Test
    public void test03() {
        int[] a = {3, 2, 1};
        slt2(a);
        Assertions.assertTrue(Arrays.equals(new int[]{1, 2, 3}, a));
    }

    /**
     * 这道题的题目不太好理解，所以出题的人也怕你看不懂，举了好几个例子。你可以理解成给定的数组的最后一位是个位，倒数第二位是十位，……，以第一个
     * 例子为例，组成的数就是：123。现在重新组合这3个元素，使其最接近123，但比123大，也就是132。
     * <p>
     * 我们拿一个长一点的数组，例如：[1,3,1,4,3,0]，首先想到的是从尾部开始往前对比，为什么从尾部，因为尾部变动对数值的影响没那么大，你修改
     * 个位、十位，肯定比修改万位、十万位变动小。现在，个位数0，能和其他位交换吗？不能，跟任何位置交换，都会使得
     * 数值变小。以此类推……，131430 -> 133410 -> 133014，十位与千位先交换，此时千位增大，随后我们需要将千位之后的数极小化。
     * 似乎可以用2层循环?不过这样时间复杂度就是O(n^2)了。
     */
    void slt(int[] a) {
        boolean flag = false;
        int j = 0;
        for (int i = a.length - 1; i > 0; i--) {
            for (j = i - 1; j >= 0; j--) {
                if (a[i] > a[j]) {
                    Utils.swap(a, i, j);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        // 现在，需要在 j 之后的元素进行升序排列
        // 这个地方个人觉得是本题最大的难点，就是如何排序
        // 这就要想通一点：j 之后的元素目前是处于【降序】的状态
        // 可以用极限想法，如果上述代码根本没有发生交换，那么整个数组其实就是降序的，或者你看上面第三个 unit test
        // 已知数组是降序的，要转成升序，这就比较简单了，就是双指针两头交换。
        int left = flag ? j + 1 : 0;
        int right = a.length - 1;
        while (left < right) {
            Utils.swap(a, left, right);
            left++;
            right--;
        }
    }

    /**
     * 一般这种时间复杂度比较高的，可以考虑优化。
     * 我们从数组的尾部开始，依次向前，判断相邻的两个是不是前一个大于后一个，则就能保证尾部的这些是【降序】，例如：[1,3,1,4,3,0]，尾部的3个
     * 元素目前就是降序的，而降序的部分在第一步是不需要交换。
     * 那么，降序是在什么地方被破坏了？就是在 i=2 的元素，我们要先找到这个位置，这就是下面代码的第一步。
     */
    void slt2(int[] a) {
        // (1)
        int i;
        for (i = a.length - 1; i > 0; i--) {
            if (a[i] > a[i - 1]) { // 比较相邻元素，判断子数组是否是降序
                break;
            }
        }
        // (2) 然后我们拿 a[i]，与它后面的某个元素交换，这个元素是最接近 a[i] 值，但又比 a[i] 大，
        //     由于我们已经知道 a[i] 后面的子数组是降序，那么就好处理了。
        if (i > 0) {
            for (int j = a.length - 1; j > i - 1; j--) {
                if (a[j] > a[i - 1]) {
                    Utils.swap(a, i - 1, j);
                    break;
                }
            }
        }
        // (3) 对 a[i] 之后的子数组进行升序。有人可能会问，经过第二步中的交换，a[i] 之后的子数组还是降序的吗？实际上还是。
        int left = i;
        int right = a.length - 1;
        while (left < right) {
            Utils.swap(a, left, right);
            left++;
            right--;
        }
    }
}
