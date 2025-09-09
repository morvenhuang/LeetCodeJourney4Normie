package com.github.morvenhuang.leetcodejourney;

import com.github.morvenhuang.leetcodejourney.Utils.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given preorder（前序遍历） and inorder（中序遍历） traversal of a tree, construct the binary tree.
 * Note:You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * ....3
 * .../ \
 * ..9  20
 * ..../ \
 * ...15  7
 * <p>
 * <p>
 * 下面是二叉树的几种遍历：
 * // 前序遍历：根 -> 左 -> 右
 * public static List<Integer> preorderTraversal(TreeNode root) {
 * List<Integer> result = new ArrayList<>();
 * preorderHelper(root, result);
 * return result;
 * }
 * <p>
 * private static void preorderHelper(TreeNode node, List<Integer> result) {
 * if (node == null) return;
 * result.add(node.val);        // 访问根节点
 * preorderHelper(node.left, result);  // 遍历左子树
 * preorderHelper(node.right, result); // 遍历右子树
 * }
 * <p>
 * // 中序遍历：左 -> 根 -> 右
 * public static List<Integer> inorderTraversal(TreeNode root) {
 * List<Integer> result = new ArrayList<>();
 * inorderHelper(root, result);
 * return result;
 * }
 * <p>
 * private static void inorderHelper(TreeNode node, List<Integer> result) {
 * if (node == null) return;
 * inorderHelper(node.left, result);  // 遍历左子树
 * result.add(node.val);        // 访问根节点
 * inorderHelper(node.right, result); // 遍历右子树
 * }
 * <p>
 * // 后序遍历：左 -> 右 -> 根
 * public static List<Integer> postorderTraversal(TreeNode root) {
 * List<Integer> result = new ArrayList<>();
 * postorderHelper(root, result);
 * return result;
 * }
 * <p>
 * private static void postorderHelper(TreeNode node, List<Integer> result) {
 * if (node == null) return;
 * postorderHelper(node.left, result);  // 遍历左子树
 * postorderHelper(node.right, result); // 遍历右子树
 * result.add(node.val);        // 访问根节点
 * }
 */
public class _0105m_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    @Test
    public void test01() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode treeNode = slt2(preorder, inorder);
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

    /**
     * * preorder(根 -> 左 -> 右) = [3,9,20,15,7]
     * * inorder(左 -> 根 -> 右) = [9,3,15,20,7]
     * <p>
     * 从前序遍历中，拿第一个元素（3），它就是 root。对应的在中序遍历中找到 3 所在的位置，那么就可以确定左子树和右子树的大小，
     * 再对左、右子树进行递归处理。
     */
    TreeNode slt(int[] preorder, int[] inorder) {
        int size = preorder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(inorder[i], i);
        }

        TreeNode treeNode = new TreeNode();
        process(preorder, 0, preorder.length - 1, map, treeNode);
        return treeNode;
    }

    /**
     * @param preorder
     * @param i        当前要处理的树，其在前序数组中的起始位置（含）
     * @param j        当前要处理的树，其在前序数组中的结束位置（含）
     * @param map      使用中序数组构建的 HashMap，便于查找
     * @param treeNode 二叉树节点
     */
    void process(int[] preorder, int i, int j, Map<Integer, Integer> map, TreeNode treeNode) {
        if (i > j) {
            return;
        } else if (i == j) {
            treeNode.val = preorder[i];
            return;
        }
        // 前序数组中的头一个元素，肯定就是当前这棵树的根。它后面跟着左子树、右子树，但这两棵子树的大小还不确定，需要依赖中序数组。
        int root = preorder[i];
        // 通过根节点在中序数组中的位置（在中序数组中，根节点的左侧就是左子树，右侧就是右子树），可以确定左右子树的大小。
        int posRoot = map.get(root);
        treeNode.val = root;
        treeNode.left = new TreeNode();
        // 左子树在前序数组中的起始、结束位置（即第二、三参数），结合具体例子比较好想
        process(preorder, i + 1, posRoot, map, treeNode.left); // left
        treeNode.right = new TreeNode();
        process(preorder, posRoot + 1, j, map, treeNode.right); // right
    }

    TreeNode slt2(int[] preorder, int[] inorder) {
        int size = preorder.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(inorder[i], i);
        }

        TreeNode treeNode = new TreeNode();
        process2(0, inorder.length - 1, preorder, 0, preorder.length - 1, map, treeNode);
        return treeNode;
    }

    /**
     * @param preorder
     * @param iPreorder 当前要处理的树，其在前序数组中的起始位置（含）
     * @param jPreorder 当前要处理的树，其在前序数组中的结束位置（含）
     * @param map       使用中序数组构建的 HashMap，便于查找
     * @param treeNode  二叉树节点
     */
    /**
     * @param iInorder  当前要处理的树，其在中序数组中的起始位置（含）
     * @param jInorder  当前要处理的树，其在中序数组中的结束位置（含）
     * @param preorder
     * @param iPreorder 当前要处理的树，其在前序数组中的起始位置（含）
     * @param jPreorder 当前要处理的树，其在前序数组中的结束位置（含）
     * @param map       使用中序数组构建的 HashMap，便于查找
     * @param treeNode  二叉树节点
     */
    void process2(int iInorder, int jInorder, int[] preorder, int iPreorder, int jPreorder, Map<Integer, Integer> map, TreeNode treeNode) {
        if (iPreorder == jPreorder) {
            treeNode.val = preorder[iPreorder];
            return;
        }
        // 前序数组中的头一个元素，肯定就是当前这棵树的根。它后面跟着左子树、右子树，但这两棵子树的大小还不确定，需要依赖中序数组。
        int root = preorder[iPreorder];
        // 通过根节点在中序数组中的位置（在中序数组中，根节点的左侧就是左子树，右侧就是右子树），可以确定左右子树的大小。
        int rootIndexInorder = map.get(root);
        int lSize = rootIndexInorder - iInorder;
        int rSize = jInorder - rootIndexInorder;
        treeNode.val = root;
        if (lSize > 0) {
            treeNode.left = new TreeNode();
            // 左子树在前序数组中的起始、结束位置（即第二、三参数），结合具体例子比较好想
            process2(iInorder, rootIndexInorder - 1, preorder, iPreorder + 1, iPreorder + lSize, map, treeNode.left); // left
        }
        if (rSize > 0) {
            treeNode.right = new TreeNode();
            process2(rootIndexInorder + 1, jInorder, preorder, jPreorder - rSize + 1, jPreorder, map, treeNode.right); // right
        }
    }

}
