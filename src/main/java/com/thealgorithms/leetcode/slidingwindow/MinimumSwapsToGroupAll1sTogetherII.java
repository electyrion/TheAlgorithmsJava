package com.thealgorithms.leetcode.slidingwindow;

public class MinimumSwapsToGroupAll1sTogetherII {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int countOne = 0;
        for (int num : nums) {
            if (num != 0) countOne++;
        }

        int windowSize = countOne;
        int countZero = 0;
        for (int i = 0; i < windowSize; i ++) {
            if (nums[i % n] != 1) countZero++;
        }   

        int minSwap = countZero;
        int left = 1, right = windowSize;
        while (left < n) {
            if (nums[left % n - 1] == 0 ) countZero--;
            if (nums[right % n] == 0) countZero++;
            minSwap = Math.min(minSwap, countZero);
            left++;
            right++;
        }

        return minSwap;
    }
}
