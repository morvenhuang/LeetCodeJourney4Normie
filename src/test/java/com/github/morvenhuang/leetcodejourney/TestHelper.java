package com.github.morvenhuang.leetcodejourney;

import com.github.morvenhuang.leetcodejourney.Utils.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class TestHelper {

    public static boolean same1DArrays(int[] a, int[] b, boolean ignoreOrder) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }

        if (a.length == 0) {
            return b.length == 0;
        }
        if (b.length == 0) {
            return a.length == 0;
        }

        if (a.length != b.length) {
            return false;
        }
        int[] a1 = Arrays.copyOfRange(a, 0, a.length);
        int[] b1 = Arrays.copyOfRange(b, 0, a.length);
        if (ignoreOrder) {
            Arrays.sort(a1);
            Arrays.sort(b1);
        }
        return Arrays.equals(a1, b1);
    }

    public static boolean same2DArrays(int[][] a, int[][] b, boolean ignoreOuterOrder, boolean ignoreInnerOrder) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }

        if (a.length == 0) {
            return b.length == 0;
        }
        if (b.length == 0) {
            return a.length == 0;
        }

        if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        }

        int[][] a1 = Arrays.stream(a).map(int[]::clone).toArray(int[][]::new);
        int[][] b1 = Arrays.stream(b).map(int[]::clone).toArray(int[][]::new);

        if (ignoreInnerOrder) {
            for (int[] arr : a1) {
                Arrays.sort(arr);
            }
            for (int[] arr : b1) {
                Arrays.sort(arr);
            }
        }

        if (ignoreOuterOrder) {
            Comparator<int[]> arrayComparator = (arr1, arr2) -> {
                for (int i = 0; i < arr1.length; i++) {
                    if (arr1[i] != arr2[i]) {
                        return arr1[i] - arr2[i];
                    }
                }
                return 0;
            };

            Arrays.sort(a1, arrayComparator);
            Arrays.sort(b1, arrayComparator);
        }

        for (int i = 0; i < a1.length; i++) {
            if (!Arrays.equals(a1[i], b1[i])) {
                return false;
            }
        }

        return true;
    }

    public static <T extends Comparable> boolean same1DLists(List<T> a, List<T> b, boolean ignoreOrder) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }

        if (a.size() == 0) {
            return b.size() == 0;
        }
        if (b.size() == 0) {
            return a.size() == 0;
        }

        List<T> a1 = new ArrayList<>(a);
        List<T> b1 = new ArrayList<>(b);
        if (ignoreOrder) {
            Collections.sort(a1);
            Collections.sort(b1);
        }
        return a.equals(b); // contain the same elements in the same order
    }

    public static <T extends Comparable> boolean same2DLists(List<List<T>> a, List<List<T>> b,
                                                             boolean ignoreOuterOrder, boolean ignoreInnerOrder) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }

        if (a.size() == 0) {
            return b.size() == 0;
        }
        if (b.size() == 0) {
            return a.size() == 0;
        }

        if (a.size() != b.size()) {
            return false;
        }

        List<List<T>> a1 = a.stream().map(d -> new ArrayList<>(d)).collect(Collectors.toList());
        List<List<T>> b1 = b.stream().map(d -> new ArrayList<>(d)).collect(Collectors.toList());

        if (ignoreInnerOrder) {
            for (int i = 0; i < a1.size(); i++) {
                Collections.sort(a1.get(i));
                Collections.sort(b1.get(i));
            }
        }

        if (ignoreOuterOrder) {
            Comparator<List<T>> listComparator = (list1, list2) -> {
                Iterator<T> it1 = list1.iterator();
                Iterator<T> it2 = list2.iterator();

                while (it1.hasNext() && it2.hasNext()) {
                    T elem1 = it1.next();
                    T elem2 = it2.next();
                    int cmp = elem1.compareTo(elem2);
                    if (cmp != 0) {
                        return cmp;
                    }
                }

                return Integer.compare(list1.size(), list2.size());
            };

            Collections.sort(a1, listComparator);
            Collections.sort(b1, listComparator);
        }

        for (int i = 0; i < a1.size(); i++) {
            List<T> la = a1.get(i);
            List<T> lb = b1.get(i);
            if (!la.equals(lb)) {
                return false;
            }
        }
        return true;
    }

    public static boolean sameBinaryTrees(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        boolean l = sameBinaryTrees(a.left, b.left);
        if (!l) {
            return false;
        }
        boolean r = sameBinaryTrees(a.right, b.right);
        if (!r) {
            return false;
        }
        return true;
    }
}
