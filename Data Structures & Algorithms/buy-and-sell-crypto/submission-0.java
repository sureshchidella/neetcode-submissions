package dsa.leetcode;

public class MaxProfit {

    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if (prices.length <= 1) {
            return 0;
        }
        for (int price : prices) {
            min = Math.min(min, price);
            max = Math.max(max, price - min);
        }

        return max;
    }


    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
