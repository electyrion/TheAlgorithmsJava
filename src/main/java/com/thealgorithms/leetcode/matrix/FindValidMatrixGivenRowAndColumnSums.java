package com.thealgorithms.leetcode.matrix;

public class FindValidMatrixGivenRowAndColumnSums {
    // 1ms
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;

        int[][] matrix = new int[m][n];
        int i = 0, j = 0;

        while (i < m && j < n) {
            matrix[i][j] = Math.min(rowSum[i], colSum[j]);
            
            rowSum[i] -= matrix[i][j];
            colSum[j] -= matrix[i][j];

            if (rowSum[i] == 0) i++;
            else j++;
        }

        return matrix;
    }

    public int[][] restoreMatrix_7ms(int[] rowSum, int[] colSum) {
        int[][] matrix = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                matrix[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= matrix[i][j];
                colSum[j] -= matrix[i][j];
            }
        }
        return matrix;
    }

    public int[][] restoreMatrix_6ms(int[] rowSum, int[] colSum) {
        int[][] matrix = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                if (rowSum[i] < colSum[j]) {
                    matrix[i][j] = rowSum[i];
                    rowSum[i] -= matrix[i][j];
                    colSum[j] -= matrix[i][j];
                } else {
                    matrix[i][j] = colSum[j];
                    rowSum[i] -= matrix[i][j];
                    colSum[j] -= matrix[i][j];
                }
            }
        }
        return matrix;
    }

    public int[][] restoreMatrix_3ms(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[] curRowSum = new int[m];
        int[] curColSum = new int[n]; 
        int[][] result = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (curRowSum[i] != rowSum[i] && curColSum[j] != colSum[j]) {
                    int fill;
                    if (rowSum[i] - curRowSum[i] < colSum[j] - curColSum[j]) {
                        fill = rowSum[i] - curRowSum[i];
                        curRowSum[i] += fill;
                        curColSum[j] += fill;
                        result[i][j] = fill;
                    } else {
                        fill = colSum[j] - curColSum[j];
                        curRowSum[i] += fill;
                        curColSum[j] += fill;
                        result[i][j] = fill;
                    }
                } 
            }
        }
        
        return result;
    }

    public int[][] restoreMatrix_also3ms(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[] curRowSum = new int[m];
        int[] curColSum = new int[n]; 
        int[][] result = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (curRowSum[i] != rowSum[i] && curColSum[j] != colSum[j]) {
                    result[i][j] = Math.min(rowSum[i] - curRowSum[i], colSum[j] - curColSum[j]);
                    curRowSum[i] += result[i][j];
                    curColSum[j] += result[i][j];
                } 
            }
        }
        
        return result;
    }
}