package dsa.leetcode;

import java.util.Arrays;

public class SearchRange {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return result;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if ()
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}
