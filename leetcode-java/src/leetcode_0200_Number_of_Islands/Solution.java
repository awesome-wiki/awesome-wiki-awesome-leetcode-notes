package leetcode_0200_Number_of_Islands;

/**
 * description: 岛屿数量
 * url: https://leetcode-cn.com/problems/number-of-islands/
 *
 * @author Michael
 * @date 2022/1/6
 * @time 11:21 下午
 */
public class Solution {
    // 岛屿数量
    Integer result = 0;

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从一个陆地开始探索
                if (grid[i][j] == '1') {
                    check(grid, i, j);
                    // 探索结束了，岛屿数量+1
                    result++;
                }
            }
        }
        return result;
    }

    private void check(char[][] grid, int i, int j) {
        // 边界检查
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        // 如果探索的格子不是陆地，表示已访问或者是水，不用继续了
        if (grid[i][j] == '0') {
            return;
        }
        // 将该陆地的格子标记为水，表示已访问
        grid[i][j] = '0';
        // 四个方向进行递归去探索
        check(grid, i, j + 1);
        check(grid, i + 1, j);
        check(grid, i, j - 1);
        check(grid, i - 1, j);
        // 注意点，这里不用将之前的 grid[i][j] 回溯了，因为我们就是想要这个格子永远的标记为已访问了
    }
}
