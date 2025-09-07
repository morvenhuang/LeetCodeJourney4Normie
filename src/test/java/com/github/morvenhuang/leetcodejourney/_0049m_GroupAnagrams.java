package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Given an array of strings, group anagrams（相同字母异序词） together.
 * <p>
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * <p>
 * Note:
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class _0049m_GroupAnagrams {

    @Test
    public void test01() {
        String[] a = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> ret = slt(a);
        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("ate", "eat", "tea"));
        expected.add(Arrays.asList("nat", "tan"));
        expected.add(Arrays.asList("bat"));
        Assertions.assertTrue(TestHelper.same2DLists(expected, ret, true, true));
    }

    // 核心问题，如何判断两词是异序词
    // 1. 对各单词中的字符进行排序，然后判等
    List<List<String>> slt(String[] a) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : a) {
            String sorted = sortChars(s);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }

    String sortChars(String s) {
        char[] chars = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);
        return new String(chars);
    }
}
