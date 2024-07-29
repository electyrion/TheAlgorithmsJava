package com.thealgorithms.leetcode.array;

/*
 * https://leetcode.com/problems/maximize-distance-to-closest-person/editorial
 */
public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int left = -1, right = 0;
        int maxDist = 0;

        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                left = i;
            } else {
                while (right < n && seats[right] == 0 || right < i) {
                    right++;
                }

                int leftDist = left == -1 ? n : i - left;
                int rightDist = right == n ? n : right - i;
                maxDist = Math.max(maxDist, Math.min(leftDist, rightDist));
            }
        }
        
        return maxDist;
    }
}
