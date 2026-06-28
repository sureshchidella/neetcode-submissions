package dsa.leetcode;

import java.util.Arrays;

public class SortColors {

    public static void sortColors(int[] nums) {
        int start = 0, end = nums.length - 1, mid = 0;

        while (mid <= end) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, start++, mid++);
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, end--);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
