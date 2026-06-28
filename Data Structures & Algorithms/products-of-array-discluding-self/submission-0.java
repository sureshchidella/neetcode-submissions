package dsa.leetcode;

import java.util.Arrays;

public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] a) {
        int n = a.length;
        int[] b = new int[n];
        b[0] = 1;
        for (int i = 1; i < n; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            b[i] = b[i] * right;
            right = right * a[i];
        }
        return b;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }
}
