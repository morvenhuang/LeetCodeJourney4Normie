package com.github.morvenhuang.leetcodejourney;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static <T extends Comparable> boolean containsList(List<List<T>> listOfList, List<T> list) {
        for (List<T> l : listOfList) {
            if (sameElements(l, list)) {
                return true;
            }
        }
        return false;
    }

    // ignore order
    public static <T extends Comparable> boolean sameElements(List<T> a, List<T> b) {
        Collections.sort(a);
        Collections.sort(b);
        // ArrayList#equals(): two lists are defined to be equal if they contain the same elements in the same order.
        return a.equals(b);
    }

    // ignore order
    public static boolean sameElements(int[] a, int[] b) {
        List<Integer> list1 = Arrays.stream(a).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(b).boxed().collect(Collectors.toList());
        return sameElements(list1, list2);
    }
}
