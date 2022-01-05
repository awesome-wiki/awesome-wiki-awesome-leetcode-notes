package leetcode_0079_Word_Search;


/**
 * description: 单词搜索
 *
 * @author Michael
 * @date 2022/1/5
 * @time 10:32 下午
 */
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