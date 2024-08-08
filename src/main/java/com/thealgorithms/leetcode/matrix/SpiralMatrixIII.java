package com.thealgorithms.leetcode.matrix;

public class SpiralMatrixIII {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] dir = new int[][] { {0,1}, {1,0}, {0,-1}, {-1,0} };
        int[][] traversed = new int[rows * cols][2];
        int idx = 0;

        // initial step size is 1, value of d represents the current direction
        for (int step = 1, direction = 0; idx < rows * cols;) {
            // direction = 0 -> east, direction = 1 -> south
            // direction = 2 -> west, direction = 3 -> north
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < step; j++) {
                    // validate the current position
                    if (
                        rStart >= 0 &&
                        rStart < rows &&
                        cStart >= 0 &&
                        cStart < cols
                    ) {
                        traversed[idx][0] = rStart;
                        traversed[idx][1] = cStart;
                        idx++;
                    }
                    // make changes to the current position
                    rStart += dir[direction][0];
                    cStart += dir[direction][1];
                }
                direction = (direction + 1) % 4;
            }
            step++;
        }
        return traversed;
    }
}
