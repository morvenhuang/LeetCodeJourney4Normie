package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon（地牢）.
 * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the
 * top-left room and must fight his way through dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to
 * 0 or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering
 * these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health
 * (represented by positive integers).
 * <p>
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * Return the knight's minimum initial health so that he can rescue the princess.
 * <p>
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room
 * where the princess is imprisoned.
 * <p>
 * <p>
 * Example 1:
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 * <p>
 * Example 2:
 * Input: dungeon = [[0]]
 * Output: 1
 * <p>
 * Constraints:
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 */
public class _0174h_DungeonGame {

    @Test
    void test01() {
        int[][] a = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        int r = slt(a);
        Assertions.assertEquals(7, r);
    }

    /**
     * 这个看着没有难度，属于最简单的 dp（动态规划）
     * 但是实际上和走迷宫还是不太一样，不能只看最后累计值，因为如果中途生命值不够，会立即死掉，游戏就进行不下去。
     * 例如上面例子中，如果只看最终总数，那么可以一直往下走然后再往右，只看总数，起始可以不用生命值，但实际上如果起始没有生命值，会直接死掉。
     * 也就是补充品不能提前使用。
     * <p>
     * 能不能倒着来，从终点往前推导？肯定不行。
     * <p>
     * 没思路，看了典型解法，就是从终点往前推导！！！
     * 以 test01 中的输入为例：
     * 1. 从右下角a[2][2]开始，到达a[2][2]的时候最少需要生命值 6
     * 2. 处理最后一行，到达a[2][1]的时候，最少生命值 1，到达a[2][0]的时候，最少生命值是1
     * 3. 处理最后一列，叨叨a[1][2]的时候，最少生命值 5，到达a[0][2]的时候，最少生命值是2
     * 4. 处理其他的，例如a[1][1]，它可以到达它的右边，以及它的下方，它的右边最少需要5生命值，它的下边最少需要1生命值，则它从a[1][1]向外走的时候，最少需要1生命值，因此进入它的时候，最少需要11生命值。
     * 5. 以此类推
     */
    int slt(int[][] a) {
        int x = a.length;
        int y = a[0].length;

        int[][] state = new int[x][y];
        state[x - 1][y - 1] = Math.max(1, 1 - a[x - 1][y - 1]);

        for (int i = x - 1; i >= 0; i--) {
            for (int j = y - 1; j >= 0; j--) {
                if (i == x - 1 && j == y - 1) {
                    continue;
                }
                if (i == x - 1) {
                    state[i][j] = Math.max(1, state[i][j + 1] - a[i][j]);
                } else if (j == y - 1) {
                    state[i][j] = Math.max(1, state[i + 1][j] - a[i][j]);
                } else {
                    int min = Math.min(state[i + 1][j], state[i][j + 1]);
                    state[i][j] = Math.max(1, min - a[i][j]);
                }
            }
        }

        return state[0][0];
    }
}
