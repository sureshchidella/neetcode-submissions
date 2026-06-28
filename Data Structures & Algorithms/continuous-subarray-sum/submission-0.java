package dsa.leetcode;

import java.util.HashMap;

public class ContinousSubArraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int rem = prefixSum % k;

            if (map.containsKey(rem)) {
                if (i - map.get(rem) >= 2) {
                    return true;
                }
            } else {
                map.put(rem, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
    }
}
