package dsa.leetcode;

public class PivotIndex {

    public static int pivotIndex(int[] nums) {
        for (int pindex = 0; pindex < nums.length; pindex++) {
            int leftSum = 0, rightSum = 0;
            for (int i = 0; i < pindex; i++) {
                leftSum += nums[i];
            }
            for (int i = pindex + 1; i < nums.length; i++) {
                rightSum += nums[i];
            }

            System.out.println("leftSum: " + leftSum);
            System.out.println("rightSum: " + rightSum);

            if (leftSum == rightSum) {
                return pindex;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }
}
