package dsa.leetcode;

public class MinSizeSubArray {
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; right < nums.length; right += 1) {
            sum += nums[right];
            while (sum >= target) {
                int currLen = right - left + 1;
                minLen = Math.min(minLen, currLen);
                sum -= nums[left];
                left += 1;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
