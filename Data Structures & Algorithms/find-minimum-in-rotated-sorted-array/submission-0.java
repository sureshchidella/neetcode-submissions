package dsa.leetcode;

public class MinimumElement {

    public static int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            int sum = 0;
            while (num > 0) {
                int rem = num % 10;
                sum = sum + rem;
                num = num / 10;
            }
            min = Math.min(sum, min);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minElement(new int[]{999, 19, 199}));
    }
}
