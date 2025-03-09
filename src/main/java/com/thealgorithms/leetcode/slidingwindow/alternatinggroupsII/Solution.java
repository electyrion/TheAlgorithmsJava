package com.thealgorithms.leetcode.slidingwindow.alternatinggroupsII;

public class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length, res = 0, count = 1;
        for (int i = 0; i < n + k - 2; i++) {
            count = colors[i % n] != colors[(i + 1) % n] ? count + 1 : 1;
            res += count >= k ? 1 : 0;
        }
        return res;
    }
}

// https://leetcode.com/problems/alternating-groups-ii/?envType=daily-question&envId=2025-03-09
