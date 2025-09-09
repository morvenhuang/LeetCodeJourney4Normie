package com.github.morvenhuang.leetcodejourney;

public class Utils {

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
