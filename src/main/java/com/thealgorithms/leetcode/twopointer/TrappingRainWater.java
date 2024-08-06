package com.thealgorithms.leetcode.twopointer;

public class TrappingRainWater {
    public int trap(int[] height) {
        int volume = 0;
        int n = height.length;
        if (n < 3) return volume;

        int left = 0, right = n - 1;
        int maxLeft = height[left], maxRight = height[right];

        while (left < right) {
            if (maxLeft < maxRight) {
                // move the left pointer
                left++;
                maxLeft = Math.max(maxLeft, height[left]);
                volume += maxLeft - height[left];
            } else {
                // move the right pointer
                right--;
                maxRight = Math.max(maxRight, height[right]);
                volume += maxRight - height[right];
            }
        }

        return volume;
    }
}
