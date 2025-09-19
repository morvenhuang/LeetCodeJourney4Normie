package com.github.morvenhuang.leetcodejourney;

import com.github.morvenhuang.leetcodejourney.Utils.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes
 * you can see ordered from top to bottom.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Explanation:
 * <p>
 * Example 2:
 * Input: root = [1,2,3,4,null,null,null,5]
 * Output: [1,3,4,5]
 * Explanation:
 * <p>
 * Example 3:
 * Input: root = [1,null,3]
 * Output: [1,3]
 * <p>
 * Example 4:
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * <p>
 * (原题有图)
 * (原题的输入是一棵树，所以不需要你从数组去构造树)
 */
public class _0199m_BinaryTreeRightSideView {

    @Test
    void test01() {
        TreeNode root = new TreeNode();
        root.val = 1;
        root.left = new TreeNode();
        root.left.val = 2;
        root.right = new TreeNode();
        root.right.val = 3;
        root.left.right = new TreeNode();
        root.left.right.val = 5;
        root.right.right = new TreeNode();
        root.right.right.val = 4;
        List<Integer> r = slt2(root);
        List<Integer> expected = Arrays.asList(1, 3, 4);
        Assertions.assertTrue(TestHelper.same1DLists(expected, r, false));
    }

    @Test
    void test02() {
        TreeNode root = new TreeNode();
        root.val = 1;
        root.left = new TreeNode();
        root.left.val = 2;
        root.right = new TreeNode();
        root.right.val = 3;
        root.left.left = new TreeNode();
        root.left.left.val = 4;
        root.left.left.left = new TreeNode();
        root.left.left.left.val = 5;
        List<Integer> r = slt2(root);
        List<Integer> expected = Arrays.asList(1, 3, 4, 5);
        Assertions.assertTrue(TestHelper.same1DLists(expected, r, false));
    }

    // 不是 bfs，而是简单的按层遍历
    List<Integer> slt(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        List<TreeNode> list1 = new ArrayList<>();
        list1.add(root);
        List<TreeNode> list2 = new ArrayList<>();
        while (!list1.isEmpty()) {
            ret.add(list1.get(0).val);
            for (TreeNode node : list1) {
                if (node.right != null) {
                    list2.add(node.right);
                }
                if (node.left != null) {
                    list2.add(node.left);
                }
            }
            list1 = new ArrayList<>(list2);
            list2.clear();
        }
        return ret;
    }

    // 稍微优化一下，使用一个队列
    List<Integer> slt2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size(); // 在往里加东西前，先记住它的长度。
            int count = 0;
            // 以下循环完成一整层
            while (count < len) {
                TreeNode poll = queue.poll();
                if (count == 0) {
                    ret.add(poll.val);
                }
                if (poll.right != null) { // 先加右子树
                    queue.offer(poll.right);
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                count++;
            }
        }
        return ret;
    }
}
