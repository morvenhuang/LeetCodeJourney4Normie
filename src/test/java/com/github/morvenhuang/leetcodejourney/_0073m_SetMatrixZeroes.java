package com.github.morvenhuang.leetcodejourney;

/**
 * Given an m*n matrix. If an element is 0, set its entire row and column to 0. Do it  in-place.
 *
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * （算法的空间复杂度是指除了原始的输入数据所占空间之外，算法执行所需额外的存储空间。这里要求最好做到常数空间，即O(1)，算法所需的额外空间不随
 * 输入大小 m 或 n 的改变而改变，是固定的）
 *
 * Example 1:
 * Input: matrix = [[1,1,1],
 *                  [1,0,1],
 *                  [1,1,1]]
 * Output: [[1,0,1],
 *          [0,0,0],
 *          [1,0,1]]
 *
 * Example 2:
 * Input: matrix = [[0,1,2,0],
 *                  [3,4,5,2],
 *                  [1,3,1,5]]
 * Output: [[0,0,0,0],
 *          [0,4,5,0],
 *          [0,3,1,0]]
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * 2^31 <= matrix[i][j] <= 2^31 - 1
 */
public class _0073m_SetMatrixZeroes {

    /**
     * 这个题的问题是，如何区别你置的0，与原始存在的0。
     * 一种就是事先记住所有原始0的坐标，但这样空间复杂度就不是常数。
     * 改进一点，就是只记住哪些行、哪些列有0，不记录具体哪些坐标，这样空间复杂度是 O(m + n)，仍然不是常数。
     *
     * 没想出来。
     * 看了网上的解法，跟算法无关，一个 trick 就是用原数组的第一行与第一列来存储状态，这样避免使用额外的空间。
     * 一开始可能会觉得，如果第一行或第一列原本就没有0，那用于记录状态后，岂不是原始值被修改了，其实是不会的，记录状态过程中，对第一行或第一列
     * 置0的位置，本来最后也是要被置0的。
     */
    int[][] slt(int[][] a) {
        // 记录初始状态下，第一行与第一列有没有0
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        for (int j = 0; j < a[0].length; j++) {
            if (a[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        // 从第二行第二列开始遍历，并使用第一行与第一列配合存储状态（有0才会改动第一行与第一列的数据）
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    a[i][0] = 0;
                    a[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a[0].length; j++) {
                if (a[i][0] == 0 || a[0][j] == 0) {
                    a[i][j] = 0;
                }
            }
        }

        if (firstRowHasZero) {
            for (int j = 0; j < a[0].length; j++) {
                a[0][j] = 0;
            }
        }

        if (firstColHasZero) {
            for (int i = 0; i < a.length; i++) {
                a[i][0] = 0;
            }
        }

        return a;
    }
}
