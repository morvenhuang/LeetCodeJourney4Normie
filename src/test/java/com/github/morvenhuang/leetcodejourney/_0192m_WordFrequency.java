package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Write a bash script to calculate the frequency of each word in a text file words.txt.
 * For simplicity sake, you may assume:
 * words.txt contains only lowercase characters and space ' ' characters.
 * Each word must consist of lowercase characters only.
 * Words are separated by one or more whitespace characters.
 * <p>
 * Example:
 * Assume that words.txt has the following content:
 * <p>
 * the day is sunny the the
 * the sunny is is
 * <p>
 * Your script should output the following, sorted by descending frequency:
 * the 4
 * is 3
 * sunny 2
 * day 1
 * <p>
 * Note:
 * Don't worry about handling ties, it is guaranteed that each word's frequency count is unique.
 * Could you write it in one-line using Unix pipes?
 */
public class _0192m_WordFrequency {

    @Test
    void test01() {
        String a = "the day is sunny the the\nthe sunny is is";
        List<WordCount> r = slt(a);
        List<WordCount> expected = Arrays.asList(new WordCount("the", 4),
                new WordCount("is", 3),
                new WordCount("sunny", 2),
                new WordCount("day", 1));
        for (int i = 0; i < 4; i++) {
            Assertions.assertEquals(expected.get(i), r.get(i));
        }
    }

    // 题目要求用 bash 脚本，这里简单写一个 java 的解法，能优化吗？
    List<WordCount> slt(String a) {
        Map<String, WordCount> map = new HashMap<>();
        String[] arr = a.split("\n");
        for (String s : arr) {
            String[] splits = s.split("\\s+");
            for (String word : splits) {
                if (!map.containsKey(word)) {
                    WordCount wc = new WordCount(word, 0);
                    map.put(word, wc);
                }
                map.get(word).count += 1;
            }
        }

        List<WordCount> list = map.values().stream().collect(Collectors.toList());
        Collections.sort(list);
        return list;
    }

    static class WordCount implements Comparable {
        String word;
        int count;

        public WordCount(String w, int c) {
            word = w;
            count = c;
        }

        @Override
        public int compareTo(Object o) {
            WordCount other = (WordCount) o;
            return other.count - count;
        }

        @Override
        public boolean equals(Object o) {
            WordCount other = (WordCount) o;
            return word.equals(other.word) && count == other.count;
        }
    }
}
