package com.thealgorithms.leetcode.math;

import java.util.TreeSet;

public class UglyNumberII {
    // https://leetcode.com/problems/ugly-number-ii/editorial
    public int nthUglyNumber(int n) {
        TreeSet<Long> uglyNumbersSet = new TreeSet<>(); // store potential ugly number
        uglyNumbersSet.add(1L); // the first ugly number
        // TreeSet automatically sorts elements in ascending order and does not allow duplicate entrie, just like a HashSet in python

        Long currentUgly = 1L;
        for (int i = 0; i < n; i++) {
            currentUgly = uglyNumbersSet.pollFirst(); // get the smallest number from the set and remove it

            // insert the next potential ugly numbers into the set
            uglyNumbersSet.add(currentUgly * 2);
            uglyNumbersSet.add(currentUgly * 3);
            uglyNumbersSet.add(currentUgly * 5);
        }

        return currentUgly.intValue();
    }
}
