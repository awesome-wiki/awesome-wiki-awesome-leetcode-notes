package leetcode_0344_Reverse_String;

/**
 * description: 344. 反转字符串
 * url: https://leetcode-cn.com/problems/reverse-string/
 *
 * @author Michael
 * @date 2022/1/12
 * @time 12:21 上午
 */
public class Solution {
    public void reverseString(char[] s) {
        String str = new StringBuilder(String.valueOf(s)).reverse().toString();
        s = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            s[i] = str.charAt(i);
        }
        System.out.println(String.valueOf(s));
    }

    public void reverseString2(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        System.out.println(String.valueOf(s));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
        solution.reverseString2(new char[]{'h', 'e', 'l', 'l', 'o'});
    }
}
