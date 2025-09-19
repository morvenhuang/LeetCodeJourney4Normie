package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Given a text file file.txt that contains a list of phone numbers (one per line), write a one-liner bash script to print all valid phone numbers.
 * You may assume that a valid phone number must appear in one of the following two formats: (xxx) xxx-xxxx or xxx-xxx-xxxx. (x means a digit)
 * You may also assume each line in the text file must not contain leading or trailing white spaces.
 * <p>
 * Example:
 * Assume that file.txt has the following content:
 * <p>
 * 987-123-4567
 * 123 456 7890
 * (123) 456-7890
 * Your script should output the following valid phone numbers:
 * 987-123-4567
 * (123) 456-7890
 */
public class _0193e_ValidPhoneNumbers {

    @Test
    void test01() {
        String a = "987-123-4567\n123 456 7890\n(123) 456-7890";
        List<String> r = slt(a);
        Assertions.assertEquals("987-123-4567", r.get(0));
        Assertions.assertEquals("(123) 456-7890", r.get(1));
    }

    // 简单写个 java 解法
    static Pattern pattern1 = Pattern.compile("^\\(\\d{3}\\) \\d{3}-\\d{4}$");
    static Pattern pattern2 = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");

    List<String> slt(String a) {
        String[] arr = a.split("\n");
        List<String> ret = new ArrayList<>();
        for (String s : arr) {
            if (pattern1.matcher(s).find() || pattern2.matcher(s).find()) {
                ret.add(s);
            }
        }
        return ret;
    }
}
