package dsa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SquareOfSortedArrays {
    public static void main(String[] args) {
        int[] nums = new int[]{-4, -5, 1, 10};
        System.out.println(Arrays.toString(squareTwoSortedArrays(nums)));
    }

    public static int[] squareTwoSortedArrays(int[] nums) {
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();

        // Separate negative and positive numbers
        for (int num : nums) {
            if (num < 0)
                neg.add(num);
            else
                pos.add(num);
        }

        // Case 1: No negative numbers
        if (neg.isEmpty()) {
            pos.replaceAll(integer -> integer * integer);
            return pos.stream().mapToInt(Integer::intValue).toArray();
        }

        // Case 2: No positive numbers
        if (pos.isEmpty()) {
            neg.replaceAll(integer -> integer * integer);
            Collections.reverse(neg);
            return neg.stream().mapToInt(Integer::intValue).toArray();
        }

        // Case 3: Both negative and positive exist
        int i, j, id = 0;
        int n1 = neg.size();
        int n2 = pos.size();
        int[] res = new int[n1 + n2];

        // Square negatives and reverse them
        neg.replaceAll(integer -> integer * integer);
        Collections.reverse(neg);

        // Square positives
        pos.replaceAll(integer -> integer * integer);

        // Merge two sorted lists
        i = 0; // j=0
        j = 0;
        while (i < n1 && j < n2) {
            if (neg.get(i) <= pos.get(j)) {
                res[id++] = neg.get(i++);
            } else {
                res[id++] = pos.get(j++);
            }
        }

        while (i < n1)
            res[id++] = neg.get(i++);

        while (j < n2)
            res[id++] = pos.get(j++);

        Math.abs(1);

        return res;
    }
}
