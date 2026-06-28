package dsa.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public static List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            // First and last element of each row is always 1
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // Sum of two elements from previous row
                    int sum = res.get(i - 1).get(j - 1) + res.get(i - 1).get(j);
                    row.add(sum);
                }
            }
            res.add(row);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}