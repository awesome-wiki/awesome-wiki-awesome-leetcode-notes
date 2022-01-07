package leetcode_0463_Island_Perimeter;

/**
 * description: 岛屿的周长
 * url: https://leetcode-cn.com/problems/island-perimeter/
 *
 * @author Michael
 * @date 2022/1/7
 * @time 11:46 下午
 */
class Solution {
    public int islandPerimeter(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    return dfs(grid, row, col);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int row, int col) {
        // 表示是陆地走向边界了，这时候边长需要返回 1
        if (!(0 <= row && row < grid.length && 0 <= col && col < grid[0].length)) {
            return 1;
        }
        // 从岛屿走向水域，边长返回1
        if (grid[row][col] == 0) {
            return 1;
        }
        // 已经访问过，返回 0
        if (grid[row][col] != 1) {
            return 0;
        }
        // 标记该位置已访问，设为 2
        grid[row][col] = 2;
        // 走到这里，表示当前格子是个陆地，下面，继续向四个方向探索，四个方向的边长返回相加，表示当前格子能够获取到的总边长
        return dfs(grid, row + 1, col) + dfs(grid, row, col + 1) + dfs(grid, row - 1, col) + dfs(grid, row, col - 1);
    }
}

class Solution2 {
    // 记录总周长
    Integer result = 0;

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    /**
     * 针对grid[i][j] 的格子进行探索，看这个位置的格子能给总周长增加多少
     *
     * @param grid 方格子
     * @param i    行
     * @param j    列
     */
    private void dfs(int[][] grid, int i, int j) {

        // 表示从陆地走向边界了，那么，周长 + 1，返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            result++;
            return;
        }
        // 该格子是已经访问过的了，周长不变
        if (grid[i][j] == 2) {
            return;
        }
        // 已经是水域了，表示是从陆地走向水域的，这时候周长 + 1，返回
        if (grid[i][j] == 0) {
            result++;
            return;
        }

        // 能够走到这里，那么，表示该方向遇到的还是陆地，标记一下该格子已访问
        grid[i][j] = 2;
        // 标记完该格子已访问，继续以当前格子为起点，向四个方向继续探索
        dfs(grid, i, j + 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
    }
}