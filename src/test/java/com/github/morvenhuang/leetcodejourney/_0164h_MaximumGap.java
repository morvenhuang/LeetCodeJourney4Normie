package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * Return 0 if the array contains less than 2 elements.
 * <p>
 * Example 1:
 * Input: [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either
 * (3,6) or (6,9) has the maximum difference 3.
 * <p>
 * <p>
 * Example 2:
 * Input: [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * <p>
 * Note:
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * Try to solve it in linear time/space.
 */
public class _0164h_MaximumGap {

    @Test
    void test01() {
        int[] a = {3, 6, 9, 1};
        int r = slt(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    void test02() {
        int[] a = {3, 6, 9, 1, 5, 8, 13};
        int r = slt(a);
        Assertions.assertEquals(4, r);
    }

    /**
     * 没有思路。
     * <p>
     * 新知识学习：桶排序。
     * 1）先用一次循环，找到数组中的 max、min
     * 2）min、max 就决定了这条线的两段，由于整个数组有 n 个元素，也即，这根线分成了 n-1 段，假设每一段长度都相等，那么每一段（即一桶）长度 bucket_size 就是：(max-min)/(n-1)。
     * 3）接着，需要想明白一点，我们所求的 maximum gap，肯定是 >= bucket_size。（如果数组本身是均匀分布，如(0,3,6,9)的时候，是 == bucket_size，这个你想象一根线上的点）
     * 4）如何对于每一个元素，计算其与起点的距离，并除以桶大小：(x-min)/bucket_size，即，计算出它落在哪个桶里。
     * 5）接着，需要想明白另外一点，落到同一个桶内的元素，最大差值 < bucket_size，这个可以随便几个例子，假设 min=0，桶大小是3，那么落在 bucket0 的只可能是 (0,1,2) 中的一些或全部。
     * 6）最后结合以上，需要想明白：a）对于一个均匀分布的数组，每一个 bucket 内只有一个元素，相邻非空 bucket 的差值正好等于 bucket_size；
     * b）对于一个非均匀数组，其最大差值肯定 > bucket_size，而桶内最大差值肯定 < bucket_size，因此只能在桶间寻找。
     * 7）综上，我们 maxGap 只要在桶间寻找即可。
     * <p>
     * 注：并没有说【每一个桶间】的差值一定大于bucket_size，
     * 例子：(0,2,3,9), bucket_size=(9-0)/3=3, bucket0=(0,2), bucket1=(3), bucket2=(), bucket3=(9)
     * bucket0 与 bucket1 的差值就小于 bucket_size，但其最大差值还是出现在（另一个）桶间，即 bucket1 与 bucket3 之间。
     */
    int slt(int[] a) {
        if (a.length == 1) {
            return 0;
        }
        // 一次循环找出最大最小值，O(n) 时间复杂度
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int v : a) {
            min = Math.min(min, v);
            max = Math.max(max, v);
        }

        int bucketSize = (max - min) / (a.length - 1);
        int bucketCount = (max - min) / bucketSize + 1;
        MinMax[] buckets = new MinMax[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new MinMax();
        }

        // 对于每一个 bucket，我们无需记录所有落入该 bucket 的数组元素，而只需要记录 bucket 内的最大最小值，即桶的边界
        for (int v : a) {
            int index = v / bucketSize;
            buckets[index].min = Math.min(buckets[index].min, v);
            buckets[index].max = Math.max(buckets[index].max, v);
        }

        int maxGap = 0;
        MinMax pre = buckets[0];
        for (int i = 1; i < bucketCount; i++) {
            if (buckets[i].max == Integer.MIN_VALUE) {
                continue;
            }
            if (pre.max != Integer.MIN_VALUE) {
                int gap = buckets[i].min - pre.max;
                maxGap = Math.max(maxGap, gap);
                pre = buckets[i];
            }
        }
        return maxGap;
    }

    static class MinMax {
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
    }
}
