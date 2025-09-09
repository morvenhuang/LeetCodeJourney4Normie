package com.github.morvenhuang.leetcodejourney;

import com.github.morvenhuang.leetcodejourney.Utils.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree. <br>
 * Note: You may assume that duplicates do not exist in the tree. <br>
 * <br>
 * For example, given <br>
 * inorder = [9,3,15,20,7] <br>
 * postorder = [9,15,7,20,3] <br>
 * Return the following binary tree: <br>
 * <br>
 * ....3 <br>
 * .../ \ <br>
 * ..9  20 <br>
 * ..../  \ <br>
 * ...15   7 <br>
 */
public class _0106m_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    @Test
    public void test01() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode treeNode = slt(inorder, postorder);
        TreeNode expected = new TreeNode();
        expected.val = 3;
        expected.left = new TreeNode();
        expected.left.val = 9;
        expected.right = new TreeNode();
        expected.right.val = 20;
        expected.right.left = new TreeNode();
        expected.right.left.val = 15;
        expected.right.right = new TreeNode();
        expected.right.right.val = 7;
        Assertions.assertTrue(TestHelper.sameBinaryTrees(expected, treeNode));
    }

    // 参见 0105
    TreeNode slt(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        TreeNode treeNode = new TreeNode();
        process(0, inorder.length - 1, postorder, 0, postorder.length - 1, map, treeNode);
        return treeNode;
    }

    // 比 0105 的 slt 复杂一点，需要传入中序数组对应的边界。而在 0105 中，前序的"根+左"，与中序的"左+根"，看成一个整体的话，索引是同步的。
    // 而 0105 的 slt2 则与这里的处理思路完全一直。
    void process(int iInorder, int jInorder, int[] postorder, int iPostorder, int jPostorder,
                 Map<Integer, Integer> map, TreeNode treeNode) {
        if (iPostorder == jPostorder) {
            treeNode.val = postorder[iPostorder];
            return;
        }
        int root = postorder[jPostorder];
        int rootIndexInorder = map.get(root);
        int lSize = rootIndexInorder - iInorder;
        int rSize = jInorder - rootIndexInorder;
        treeNode.val = root;
        if (lSize > 0) {
            treeNode.left = new TreeNode();
            process(iInorder, rootIndexInorder - 1, postorder, iPostorder, iPostorder + lSize - 1, map, treeNode.left);
        }
        if (rSize > 0) {
            treeNode.right = new TreeNode();
            process(rootIndexInorder + 1, jInorder, postorder, jPostorder - rSize, jPostorder - 1, map, treeNode.right);
        }
    }
}
