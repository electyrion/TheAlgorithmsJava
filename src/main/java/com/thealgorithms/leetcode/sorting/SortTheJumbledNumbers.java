package com.thealgorithms.leetcode.sorting;

import java.util.ArrayList;
import java.util.List;

public class SortTheJumbledNumbers {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        // Create a list of pairs (nums[i], mapped value)
        List<int[]> pairList = new ArrayList<>();
        for (int num : nums) {
            pairList.add(new int[]{num, getMappedValue(num, mapping)});
        }

        // Sort the list based on the mapped values
        pairList.sort((a, b) -> Integer.compare(a[1], b[1]));

        // Extract the sorted elements back into the nums array
        for (int i = 0; i < nums.length; i++) {
            nums[i] = pairList.get(i)[0];
        }

        return nums;
    }

    // Helper method to get the mapped value of a number
    private int getMappedValue(int num, int[] mapping) {
        StringBuilder sb = new StringBuilder();
        String numStr = Integer.toString(num);
        for (char c : numStr.toCharArray()) {
            sb.append(mapping[c - '0']);
        }
        return Integer.parseInt(sb.toString());
    }
}
