package dsa.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {

    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            int num = nums[i % n];
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }
            if (i < n && !stack.isEmpty()) {
                res[i] = stack.peek();
            }
            stack.push(num);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 3, 4, 3})));
    }
}
