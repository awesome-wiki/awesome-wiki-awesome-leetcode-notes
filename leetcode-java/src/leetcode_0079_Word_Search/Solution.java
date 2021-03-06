package leetcode_0079_Word_Search;

/**
 * description: 79. 单词搜索 Word Search
 *
 * @author Michael
 * @date 2022/1/4
 * @time 11:06 下午
 */
public class Solution {
    // 方向数组，顺时针方向，上右下左
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // 初始化数组用于记录格子是否访问，默认值是 false，表示未访问
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean check = dfs(board, word, i, j, 0, visited);
                if (check) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, String word, int m, int n, int index, boolean[][] visited) {
        // 检查当前探索的格子是否超过边界
        if (m < 0 || m >= board.length || n < 0 || n >= board[0].length) {
            return false;
        }
        // 如果已经访问，则不能继续，这个判断不能放到 42-47 代码行的后面！
        if (visited[m][n]) {
            return false;
        }
        // 检查当前探索的格子中的字符是否正好是单词对应位置的字符
        if (board[m][n] != word.charAt(index)) {
            return false;
        }
        // 如果探索的位置字符正好和单词对应位置字符一致，且，长度正好又一致了，表示找到满足条件的了，返回 true
        if (index == word.length() - 1) {
            return true;
        }


        // 标记当前格子已访问
        visited[m][n] = true;
        // 尝试向当前格子的四个方向进行递归探索
        for (int[] direction : directions) {
            boolean flag = dfs(board, word, m + direction[0], n + direction[1], index + 1, visited);
            // 一旦某个方向上探索到满足目标的结果，则停止，不用继续探索浪费时间了
            if (flag) {
                return true;
            }
        }
        // 回溯，恢复之前的状态
        visited[m][n] = false;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] demo1 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        char[][] demo2 = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        String word4 = "oath";
        Solution2 solution2 = new Solution2();
        // true
        System.out.println(solution.exist(demo1, word1));
        System.out.println(solution2.exist(demo1, word1));
        // true
        System.out.println(solution.exist(demo1, word2));
        System.out.println(solution2.exist(demo1, word2));
        // false
        System.out.println(solution.exist(demo1, word3));
        System.out.println(solution2.exist(demo1, word3));
        // true
        System.out.println(solution.exist(demo2, word4));
        System.out.println(solution2.exist(demo2, word4));
    }
}

class Solution2 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, String word, int i, int j, int index) {
        // 边界检查
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        // 格子已经访问过，返回
        if (board[i][j] == '*') {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        // 暂存一下当前格子的字符，方便回溯
        char temp = board[i][j];
        // 用一个 * 表示该格子已访问
        board[i][j] = '*';
        // 这里 java 的语法，只要某一个递归方向遇到有 true 的，则其他路并不会也去执行的
        boolean flag = check(board, word, i, j + 1, index + 1)
                || check(board, word, i + 1, j, index + 1)
                || check(board, word, i, j - 1, index + 1)
                || check(board, word, i - 1, j, index + 1);
        // 回溯
        board[i][j] = temp;
        return flag;
    }
}