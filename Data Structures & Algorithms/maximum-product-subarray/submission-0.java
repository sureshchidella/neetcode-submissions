package dsa.leetcode;

public class MaxSubArrayProduct {

    public static int maxProduct(int[] nums) {
        int product = 1, maxProduct = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num == 0) continue;
            product *= num;
            maxProduct = Math.max(product, maxProduct);
            product = product < 0 ? 1 : product;
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, 4}));
    }
}
