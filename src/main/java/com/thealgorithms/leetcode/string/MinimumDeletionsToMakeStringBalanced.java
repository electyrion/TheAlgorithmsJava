package com.thealgorithms.leetcode.string;


/*
 * https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/editorial
 */
public class MinimumDeletionsToMakeStringBalanced {
    public int minimumDeletions(String s) {
        int n = s.length();

        // first pass: count the number of a's
        int aCount = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') aCount++;
        }

        int bCount = 0;
        int minDeletions = n;

        // second pass: iterate to compute min deletions
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') aCount--;
            minDeletions = Math.min(minDeletions, aCount + bCount);
            if (s.charAt(i) == 'b') bCount++;
        }
        
        return minDeletions;
    }
}
