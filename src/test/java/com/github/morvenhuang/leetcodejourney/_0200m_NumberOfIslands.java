package com.github.morvenhuang.leetcodejourney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.(即数组之外都是水)
 * <p>
 * Example 1:
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * <p>
 * Example 2:
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class _0200m_NumberOfIslands {

    @Test
    void test01() {
        String[][] a = {
                {"1", "1", "1", "1", "0"},
                {"1", "1", "0", "1", "0"},
                {"1", "1", "0", "0", "0"},
                {"0", "0", "0", "0", "0"}
        };
        int r = slt3(a);
        Assertions.assertEquals(1, r);
    }

    @Test
    void test02() {
        String[][] a = {
                {"1", "1", "0", "0", "0"},
                {"1", "1", "0", "0", "0"},
                {"0", "0", "1", "0", "0"},
                {"0", "0", "0", "1", "1"}
        };
        int r = slt(a);
        Assertions.assertEquals(3, r);
    }

    @Test
    void test03() {
        String[][] a = {
                {"1", "1", "1"},
                {"0", "1", "0"},
                {"1", "1", "1"}
        };
        int r = slt(a);
        Assertions.assertEquals(1, r);
    }

    @Test
    void test04() {
        String[][] a = {
                {"1", "1", "1", "1"},
                {"1", "0", "0", "1"},
                {"1", "0", "0", "1"},
                {"1", "1", "1", "1"}
        };
        int r = slt(a);
        Assertions.assertEquals(1, r);
    }

    @Test
    public void testComplexShape() {
        String[][] grid = {
                {"1", "0", "1", "1"},
                {"1", "0", "0", "1"},
                {"1", "0", "1", "1"},
                {"1", "1", "1", "0"}
        };
        Assertions.assertEquals(1, slt(grid));
    }

    @Test
    public void testSpiralIsland() {
        String[][] grid = {
                {"1", "1", "1", "1", "1"},
                {"1", "0", "0", "0", "1"},
                {"1", "0", "1", "0", "1"},
                {"1", "0", "0", "0", "1"},
                {"1", "1", "1", "1", "1"}
        };
        Assertions.assertEquals(2, slt(grid));
    }

    // 感觉递归比较好写，但 1 <= m, n <= 300，递归可能会溢出
    // 写一个比较土的解法
    int slt(String[][] a) {
        Map<String, Integer> pos2Island = new HashMap<>();
        Map<Integer, Set<String>> island2PosSet = new HashMap<>();

        int id = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j].equals("1")) {
                    String kCurr = getKey(i, j);
                    String kUp = getKey(i - 1, j);
                    String kLeft = getKey(i, j - 1);
                    Integer islandUp = pos2Island.get(kUp);
                    Integer islandLeft = pos2Island.get(kLeft);
                    if (islandUp != null && islandLeft != null) {
                        if (!islandUp.equals(islandLeft)) {
                            // merge
                            Set<String> posSetUp = island2PosSet.get(islandUp);
                            Set<String> posSetLeft = island2PosSet.remove(islandLeft);
                            posSetUp.addAll(posSetLeft);
                            for (String s : posSetLeft) {
                                pos2Island.put(s, islandUp);
                            }
                        }
                        pos2Island.put(kCurr, islandUp);
                        island2PosSet.get(islandUp).add(kCurr);
                    } else if (islandUp != null) {
                        pos2Island.put(kCurr, islandUp);
                        island2PosSet.get(islandUp).add(kCurr);
                    } else if (islandLeft != null) {
                        pos2Island.put(kCurr, islandLeft);
                        island2PosSet.get(islandLeft).add(kCurr);
                    } else {
                        pos2Island.put(kCurr, id);
                        island2PosSet.put(id, new HashSet<>());
                        island2PosSet.get(id).add(kCurr);
                        id++;
                    }
                }
            }
        }

        return island2PosSet.size();
    }

    String getKey(int i, int j) {
        return i + ":" + j;
    }

    // dfs，可能会溢出
    int slt2(String[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j].equals("1")) {
                    dfs(a, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void dfs(String[][] a, int i, int j) {
        if (i < 0 || i >= a.length || j < 0 || j >= a[0].length || a[i][j].equals("0")) {
            return;
        }
        a[i][j] = "0";
        dfs(a, i - 1, j);
        dfs(a, i + 1, j);
        dfs(a, i, j - 1);
        dfs(a, i, j + 1);
    }

    // bfs (Breadth-First Search，广度优先搜索)
    // 这是本题的经典解法，广度优先避免了递归
    int slt3(String[][] a) {
        int count = 0;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j].equals("0")) {
                    continue;
                }
                queue.offer(new int[]{i, j});
                a[i][j] = "0";

                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    if (check(a, poll[0] - 1, poll[1])) { // up
                        queue.offer(new int[]{poll[0] - 1, poll[1]});
                        a[poll[0] - 1][poll[1]] = "0";
                    }
                    if (check(a, poll[0] + 1, poll[1])) { // down
                        queue.offer(new int[]{poll[0] + 1, poll[1]});
                        a[poll[0] + 1][poll[1]] = "0";
                    }
                    if (check(a, poll[0], poll[1] - 1)) { // left
                        queue.offer(new int[]{poll[0], poll[1] - 1});
                        a[poll[0]][poll[1] - 1] = "0";
                    }
                    if (check(a, poll[0], poll[1] + 1)) { // right
                        queue.offer(new int[]{poll[0], poll[1] + 1});
                        a[poll[0]][poll[1] + 1] = "0";
                    }
                }

                count++;
            }
        }

        return count;
    }

    boolean check(String[][] a, int i, int j) {
        if (i < 0 || j < 0 || i >= a.length || j >= a[0].length) {
            return false;
        }
        return a[i][j].equals("1");
    }
}
