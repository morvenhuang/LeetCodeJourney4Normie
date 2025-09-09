package com.github.morvenhuang.leetcodejourney;

import com.github.morvenhuang.leetcodejourney.Utils.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST. <br>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees <br>
 * of every node never differ by more than 1. <br>
 * <p>
 * Example: <br>
 * Given the sorted array: [-10,-3,0,5,9], <br>
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST: <br>
 * ......0 <br>
 * ...../ \ <br>
 * ...-3   9 <br>
 * .../   / <br>
 * .-10  5 <br>
 */
public class _0108e_ConvertSortedArrayToBinarySearchTree {

    @Test
    public void test01() {
        int[] a = {-10, -3, 0, 5, 9};
        TreeNode treeNode = slt(a);

        TreeNode expected = new TreeNode();
        expected.val = 0;
        expected.left = new TreeNode();
        expected.left.val = -3;
        expected.left.left = new TreeNode();
        expected.left.left.val = -10;
        expected.right = new TreeNode();
        expected.right.val = 9;
        expected.right.left = new TreeNode();
        expected.right.left.val = 5;

        Assertions.assertTrue(TestHelper.sameBinaryTrees(expected, treeNode));
    }

    /**
     * 构建高度平衡二叉树，用数组的中间元素做根节点，然后左右递归处理。
     */
    TreeNode slt(int[] a) {
        TreeNode treeNode = new TreeNode();
        process(a, 0, a.length - 1, treeNode);
        return treeNode;
    }

    void process(int[] a, int i, int j, TreeNode treeNode) {
        if (i == j) {
            treeNode.val = a[i];
            return;
        }
        int mid = i + (j - i + 1) / 2;
        treeNode.val = a[mid];
        if (mid - 1 >= i) {
            treeNode.left = new TreeNode();
            process(a, i, mid - 1, treeNode.left);
        }
        if (j >= mid + 1) {
            treeNode.right = new TreeNode();
            process(a, mid + 1, j, treeNode.right);
        }
    }
}
