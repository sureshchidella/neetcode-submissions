package dsa.leetcode;

public class PartitionEqualsSubsetSum {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int k : nums) {
            sum += k;
        }
        if (sum % 2 != 0) return false;
        boolean[] dp = new boolean[10001];
        dp[0] = true;
        for (int num : nums) {
            for (int j = 10000; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[sum / 2])
                return true;
        }
        return dp[sum / 2];
    }
}
