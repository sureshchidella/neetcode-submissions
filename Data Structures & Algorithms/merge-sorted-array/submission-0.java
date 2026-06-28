package dsa.leetcode;

import java.util.Arrays;

public class MergeSortedArrays {

    public static int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] result = new int[n + m];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }

        while (i < n) {
            result[k++] = nums1[i++];
        }

        while (j < m) {
            result[k++] = nums2[j++];
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 4, 6};

        System.out.println(Arrays.toString(
                mergeSortedArrays(nums1, nums2)
        ));
    }
}