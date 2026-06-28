package dsa.leetcode;

public class MinimumWindowSubString {
    public static String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen || sLen == 0 || tLen == 0) return "";

        int[] freq = new int[128];
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int left = 0, right = 0, minStart = 0;
        int minLen = Integer.MAX_VALUE;
        int required = tLen;
        char[] charS = s.toCharArray();

        while (right < sLen) {
            if (freq[charS[right]]-- > 0) {
                required--;
            }
            while (required == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                if (++freq[charS[left]] > 0) {
                    required++;
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
