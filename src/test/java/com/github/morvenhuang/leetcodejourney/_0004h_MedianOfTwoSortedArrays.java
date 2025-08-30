package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * The median is 2.0
 *
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 * <p>
 * 注：对于一个数组来说，所谓的"中位数"，当数组长度为奇数，则就是中间那个数；如果是偶数，则是中间2个数的均值。
 */
public class _0004h_MedianOfTwoSortedArrays {

    @Test
    public void test01() { // contains
        int[] a = new int[]{0, 7};
        int[] b = new int[]{2, 5, 6};
        double d = slt2(a, b);
        Assertions.assertEquals(5.0, d, 1e-10);
    }

    @Test
    public void test02() { // contains
        int[] a = new int[]{3, 4};
        int[] b = new int[]{2, 5, 6};
        double d = slt2(a, b);
        Assertions.assertEquals(4.0, d, 1e-10);
    }

    @Test
    public void test03() { // no overlap
        int[] a = new int[]{0, 7};
        int[] b = new int[]{10, 20, 30};
        double d = slt2(a, b);
        Assertions.assertEquals(10.0, d, 1e-10);
    }

    @Test
    public void test04() { // no overlap
        int[] a = new int[]{5, 7};
        int[] b = new int[]{1, 2, 3};
        double d = slt2(a, b);
        Assertions.assertEquals(3.0, d, 1e-10);
    }

    @Test
    public void test05() { // overlap
        int[] a = new int[]{1, 3, 5};
        int[] b = new int[]{2, 4, 6};
        double d = slt2(a, b);
        Assertions.assertEquals(3.5, d, 1e-10);
    }

    @Test
    public void test06() { // overlap
        int[] a = new int[]{1};
        int[] b = new int[]{2};
        double d = slt2(a, b);
        Assertions.assertEquals(1.5, d, 1e-10);
    }

    @Test
    public void test07() { // overlap
        int[] a = new int[]{1, 3, 5, 7};
        int[] b = new int[]{2, 4, 6};
        double d = slt2(a, b);
        Assertions.assertEquals(4, d, 1e-10);
    }

    // 最直接，合并数组，然后计算下标，取中位数，但达不到 O(log (m+n))，应该是 O(m+n)
    double slt(int[] a, int[] b) {
        List<Integer> all = new ArrayList<>();
        int idx1 = 0;
        int idx2 = 0;
        while (true) {
            if (a[idx1] <= b[idx2]) {
                all.add(a[idx1]);
                idx1++;
                if (idx1 == a.length) {
                    break;
                }
            } else {
                all.add(b[idx2]);
                idx2++;
                if (idx2 == b.length) {
                    break;
                }
            }
        }
        for (int i = idx1; i < a.length; i++) {
            all.add(a[i]);
        }
        for (int i = idx2; i < b.length; i++) {
            all.add(b[i]);
        }

        if (all.size() % 2 == 1) {
            return all.get(all.size() / 2) * 1.0;
        }
        return (all.get(all.size() / 2) + all.get(all.size() / 2 - 1)) / 2.0;
    }

    /**
     * --------------- 前提 ---------------
     * 1. 需要了解数组中位数的概念。
     * 2. 需要注意，两个数组合并后的长度是已知的，即 m+n，本题中不需要你考虑去重操作。
     *
     *
     * --------------- 思路 ---------------
     * 想象两个数组是两条线段，有交叠部分（其实也可能没交叠，属于边界情况），沿正中间切一刀，假设这一刀会同时切到 a、b 两个线段（也可能只切到一条，属于边界情况）。
     * 假设这一刀切在了 a 的索引 x 与 x+1 之间的"缝隙"，切在了 b 的索引 y 与 y+1 之间的"缝隙"，那么切线左侧的元素总量就是 x+y，它需要满足：x+y=(m+n)/2。
     * 也就是，当 x 定了，就可以推导出 y。
     *
     * 初始时，随机（或者二分）选一个 x，再推导出 y，这时需要满足切线左侧的数必须比右侧的数小：
     *   a[x]<=a[x+1] （a是有序数组，肯定满足，不用考虑）
     *   a[x]<=b[y+1]
     *   b[y]<=b[y+1] （b是有序数组，肯定满足，不用考虑）
     *   b[y]<=a[x+1]
     * 如果不满足呢？说明切线位置不对，需要移动。例如，如果 a[x]>b[y+1]，则 a 数组的切线要往左移动。
     * 怎么移动？步长多少？从题目的 O(log (m+n)) 给我们一个提示，可以用二分法移动切线。
     *
     *
     * --------------- 难点 ---------------
     * 不合并数组，而是在两个数组间直接寻找"切线"位置，这个思路不难，本题比较难的点，我个人觉得是边界情况。
     * 上面我所做的假设，切线是在 x、y 之后的，也就是，a[x], b[y] 这两个元素在切线左侧。（当然，你的切线也可以假设是在 x, y 之前，那么相应的一些代码细节就需要调整）
     *
     * 1. 满足 x+y=(m+n)/2，那么如果 a、b 两个数组总长是奇数，那么中位数就是最靠近切线的右侧的那个数；如果是偶数，则是切线两侧各取一个最靠近切线的。
     * 2. 二分法的左右边界到底应该怎么取？在二分法中，索引是有可能到达左右边界（含）内的所有数据点，所以这两个边界的取值很重要，需要能覆盖本题中的各种特殊情况。
     *    为什么左边界设置成-1？这和我最开始设置的切线规则有关系，如果左边界设置成0，那么切线最多位于 a[0], a[1] 之间，无法表示一个特殊情况，即整个 a 数组的所有元素，都在切线右侧（a 数组的元素值特别大）。
     *    为什么右边界设置成 len-1？因为这时，切线位置可以到达 a[len-1], a[len] 之间，这已经超出数组边界，可以表示一种特殊情况，即整个 a 数组的所有元素，都在切线的左侧。
     */
    double slt2(int[] a, int[] b) {
        int lb = -1; // 这里需要注意，二分法的左边界应该如何取值
        int rb = a.length - 1;
        int x = 0, y = 0;
        int half = (a.length + b.length) / 2;

        while (lb <= rb) {
            x = lb + (rb - lb) / 2; // 标准2分写法
            y = half - x - 2; // 假设切线同样在y索引的右侧，意味着切线左侧(含y)**数量**必须是 half-(x+1)，索引比数量少1，应该再-1
            // 由于这里二分法的边界，我们实际上是允许 x 到达-1，所以为了避免索引溢出，需要判断 x > -1
            if (x > -1 && a[x] > b[y + 1]) {
                rb = x - 1;
                continue;
            }
            // y 也可能到达-1，所以为了避免索引溢出，需要判断 y > -1
            if (x < a.length - 1 && y > -1 && b[y] > a[x + 1]) {
                lb = x + 1;
                continue;
            }
            break;
        }

        boolean odd = (a.length + b.length) % 2 == 1;

        int left;
        int right;
        if (x == -1) { // 表示数组a在切线左侧没有数据，因此确定切线左侧只有b数组的数据
            left = b[y];
        } else if (y == -1) {
            left = a[x];
        } else {
            left = Math.max(a[x], b[y]); // 最靠近切线左侧的
        }

        if (x == a.length - 1) {
            right = b[y + 1];
        } else if (y == b.length - 1) {
            right = a[x + 1];
        } else {
            right = Math.min(a[x + 1], b[y + 1]); // 最靠近切线右侧的
        }

        if (odd) {
            return right; // 根据最开始设定的切线原则，这里需要返回的是切线右侧的数。
        } else {
            return (left + right) / 2d;
        }
    }


}
