package dsa.leetcode;

import java.util.Arrays;

public class MaximumSumCircularSubArray {

    public static int maxSubarraySumCircular(int[] nums) {
        int maxSum = Integer.MIN_VALUE, sum = 0;
        int[] arr = new int[nums.length*2];
        System.arraycopy(nums, 0, arr, 0, nums.length);
        System.arraycopy(nums, 0, arr, nums.length, nums.length);

        for (int i = 0; i < nums.length; i++) {
            if (arr[i] > 0) {
                sum += arr[i];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }


    public static void main(String[] args) {
        System.out.println(maxSubarraySumCircular(new int[]{5, -3, 5}));
    }
}
