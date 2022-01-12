package leetcode_167_TwoSumII;

/**
 * description:
 *
 * @author Michael
 * @date 2022/1/13
 * @time 12:03 上午
 */
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int temp = numbers[left] + numbers[right];
            if (temp < target) {
                left++;
            } else if (temp > target) {
                right--;
            } else {
                // 因为 numbers 从 1 开始计数，因此，返回时，把索引值 + 1
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }

    // 二分查找
    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }
}
