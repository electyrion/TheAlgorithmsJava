package com.thealgorithms.leetcode.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> {
            int freqCompare = map.get(a).compareTo(map.get(b));
            if (freqCompare == 0) {
                return Integer.compare(b, a);
            }
            return freqCompare;
        });

        int[] sortedNums = new int[nums.length];
        int index = 0;
        for (int num : list) {
            int freq = map.get(num);
            for (int i = 0; i < freq; i++) {
                sortedNums[index++] = num;
            }
        }

        return sortedNums;
    }
}
