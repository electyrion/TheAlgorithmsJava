package com.thealgorithms.leetcode.slidingwindow.minimumrecolorstogetkconsecutiveblackblocks;

public class Solution {
    public int minimumRecolors(String blocks, int k) {
        int left = 0;
        int right = 0;
        int whiteCount = 0;
        int minRecolors = Integer.MAX_VALUE;

        while (right < blocks.length()) {
            if (blocks.charAt(right) == 'W') {
                whiteCount++;
            }

            if (right - left + 1 == k) {
                minRecolors = Math.min(minRecolors, whiteCount);
                if (blocks.charAt(left) == 'W') {
                    whiteCount--;
                }
                left++;
            }
            right++;
        }
        return minRecolors;
    }
}

// https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/description/