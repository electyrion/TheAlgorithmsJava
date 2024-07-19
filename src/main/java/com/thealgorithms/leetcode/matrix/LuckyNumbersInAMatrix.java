package com.thealgorithms.leetcode.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LuckyNumbersInAMatrix {
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> luckyNumbers = new ArrayList<>();
        int[] rowMin = new int[matrix.length];
        int[] colMax = new int[matrix[0].length];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        Arrays.fill(colMax, Integer.MIN_VALUE);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }
        for (int i = 0; i < rowMin.length; i++) {
            for (int j = 0; j < colMax.length; j++) {
                if (rowMin[i] == colMax[j]) {
                    luckyNumbers.add(rowMin[i]);
                }
            }
        }
        return luckyNumbers;
    }
}
