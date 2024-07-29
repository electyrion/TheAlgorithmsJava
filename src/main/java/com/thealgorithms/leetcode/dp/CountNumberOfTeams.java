package com.thealgorithms.leetcode.dp;

/*
 * https://leetcode.com/problems/count-number-of-teams/editorial
 */
public class CountNumberOfTeams {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int count = 0;

        for (int j = 1; j < n - 1; j++) {
            int lessLeft = 0, greaterLeft = 0;
            int lessRight = 0, greaterRight = 0;

            // Count elements less and greater than rating[j] on the left side
            for (int i = 0; i < j; i++) {
                if (rating[i] < rating[j]) {
                    lessLeft++;
                } else if (rating[i] > rating[j]) {
                    greaterLeft++;
                }
            }

            // Count elements less and greater than rating[j] on the right side
            for (int k = j + 1; k < n; k++) {
                if (rating[k] < rating[j]) {
                    lessRight++;
                } else if (rating[k] > rating[j]) {
                    greaterRight++;
                }
            }

            // Forming teams
            count += lessLeft * greaterRight + greaterLeft * lessRight;
        }

        return count;
    }
}
