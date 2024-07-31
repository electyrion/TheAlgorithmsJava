package com.thealgorithms.leetcode.dp.one;

public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        
        dp[n] = 0; // base case: no cost to step off the end of the stairs
        dp[n-1] = cost[n-1]; // base case: cost to step on the last stair

        for (int i = n-2; i >= 0; i--) {
            dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
        }

        return Math.min(dp[0], dp[1]);
    }
}
