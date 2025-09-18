package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 * <p>
 * Example 1:
 * Input: nums = [10,2]
 * Output: "210"
 * <p>
 * Example 2:
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class _0179m_LargestNumber {

    @Test
    void test01() {
        int[] a = {3, 30, 34, 5, 9};
        String r = slt(a);
        Assertions.assertEquals("9534330", r);
    }

    @Test
    void test02() {
        int[] a = {10, 2};
        String r = slt(a);
        Assertions.assertEquals("210", r);
    }

    String slt(int[] a) {
        List<Foobar> list = new ArrayList<>();
        for (int i : a) {
            Foobar foobar = new Foobar(i);
            list.add(foobar);
        }
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for (Foobar f : list) {
            sb.append(f.val);
        }
        return sb.toString();
    }

    static class Foobar implements Comparable {
        Integer val;

        public Foobar(int i) {
            val = i;
        }

        // 也没错，但有点想复杂了
//        @Override
//        public int compareTo(Object o) {
//            Foobar foobar = (Foobar) o;
//            String other = foobar.val.toString();
//            String s = val.toString();
//            if (s.length() == other.length()) {
//                return -s.compareTo(other); // 这里是逆序
//            }
//
//            int maxLen = Math.max(s.length(), other.length());
//            for (int i = 0; i < maxLen; i++) {
//                // 假如两个数，一个是2位，一个是1位数
//                // 现在的问题是，如果左侧第一位相同（例如这两个数分别是：x=30, y=3），接下来第二位怎么比，从两种连接方式：303 vs 330，可以看出来，在比较第二位时，对于y，直接区首位即可。
//                char a = i < s.length() ? s.charAt(i) : s.charAt(0);
//                char b = i < other.length() ? other.charAt(i) : other.charAt(0);
//                if (a != b) {
//                    return b - a; // 这里是逆序
//                }
//            }
//            return 0;
//        }

        @Override
        public int compareTo(Object o) {
            Foobar foobar = (Foobar) o;
            String other = foobar.val.toString();
            String s = val.toString();
            return (other + s).compareTo(s + other);
        }
    }
}
