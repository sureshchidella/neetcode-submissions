package dsa.arrays;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5}; // You can change this array
        int k = 2; // You can also try any k value here

        System.out.println("Original: " + Arrays.toString(nums));
        rotate(nums, k);
        System.out.println("Rotated : " + Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k % n == 0) return;

        k = k % n; // Normalize k (in case k > n)

        // Step 1: Reverse whole array
        reverse(nums, 0, n - 1);

        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    // Helper to reverse part of the array
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
