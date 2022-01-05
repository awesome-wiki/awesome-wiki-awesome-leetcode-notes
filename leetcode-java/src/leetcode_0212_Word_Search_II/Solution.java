package leetcode_0212_Word_Search_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description:
 *
 * @author Michael
 * @date 2022/1/5
 * @time 10:57 下午
 */
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Set<String> set = new HashSet<>(Arrays.asList(words));
        List<String> result = new ArrayList<>();
        for (String word : set) {
            boolean flag = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    flag = check(board, word, i, j, 0);
                    if (flag) {
                        result.add(word);
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        return result;
    }

    private boolean check(char[][] board, String word, int i, int j, int index) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] == '*') {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        char temp = board[i][j];
        board[i][j] = '*';
        boolean flag = check(board, word, i, j + 1, index + 1)
                || check(board, word, i + 1, j, index + 1)
                || check(board, word, i, j - 1, index + 1)
                || check(board, word, i - 1, j, index + 1);
        board[i][j] = temp;
        return flag;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution2 solution2 = new Solution2();
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        System.out.println(solution.findWords(board, words));
        System.out.println(solution2.findWords(board, words));
    }
}

class Solution2 {
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        Set<String> set = new HashSet<>(Arrays.asList(words));
        List<String> result = new ArrayList<>();
        int[] count = countEachLetterInBoard(board);
        for (String word : set) {
            int[] curr = countEachLetterInWord(word);
            boolean valid = isValid(count, curr);
            if (!valid) {
                System.out.println("预校验就不通过，因为 board 中对应字母的个数没有 word 中的多，肯定就不会找到了，word: " + word);
                continue;
            }
            boolean flag = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    flag = check(board, word, i, j, 0);
                    if (flag) {
                        result.add(word);
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        return result;
    }

    private boolean isValid(int[] count, int[] curr) {
        boolean valid = true;
        for (int i = 0; i < 26; i++) {
            if (count[i] < curr[i]) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private int[] countEachLetterInWord(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        System.out.println("word: " + Arrays.toString(count) + word);
        return count;
    }

    private int[] countEachLetterInBoard(char[][] board) {
        int[] count = new int[26];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                count[board[i][j] - 'a']++;
            }
        }
        System.out.println("board:" + Arrays.toString(count));
        return count;
    }

    private boolean check(char[][] board, String word, int i, int j, int index) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] == '*') {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        char temp = board[i][j];
        board[i][j] = '*';
        boolean flag = check(board, word, i, j + 1, index + 1)
                || check(board, word, i + 1, j, index + 1)
                || check(board, word, i, j - 1, index + 1)
                || check(board, word, i - 1, j, index + 1);
        board[i][j] = temp;
        return flag;
    }
}
