package com.thealgorithms.leetcode.dp;


/*
 * https://leetcode.com/problems/filling-bookcase-shelves/editorial
 */
public class FillingBookcaseShelves {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        // cache to store previous computations
        int[][] memo = new int[books.length][shelfWidth+1];
        return dpHelper(books, shelfWidth, memo, 0, shelfWidth, 0);
    }

    private int dpHelper(
        int[][] books,
        int shelfWidth,
        int[][] memo,
        int i,
        int remainingShelfWidth,
        int maxHeight
    ) {
        int[] currentBook = books[i];
        int maxHeightUpdated = Math.max(maxHeight, currentBook[1]);
        if (i == books.length - 1) {
            // for the last book, store it in the current shelf if possible,
            // or start a new shelf with it
            if (remainingShelfWidth >= currentBook[0]) return maxHeightUpdated;
            else return maxHeight + currentBook[1];
        }

        // return answer if already computed
        if (memo[i][remainingShelfWidth] != 0) {
            return memo[i][remainingShelfWidth];
        } else {
            // calculate the height of the bookcase if we put the current book on the new shelf
            int option1Height = 
                maxHeight + 
                dpHelper(
                    books,
                    shelfWidth,
                    memo,
                    i + 1,
                    shelfWidth - currentBook[0],
                    currentBook[1]
                );

            if (remainingShelfWidth >= currentBook[0]) {
                // calculate height of the bookcase if we put the current book on the current shelf
                int option2Height = dpHelper(
                    books,
                    shelfWidth,
                    memo,
                    i + 1,
                    remainingShelfWidth - currentBook[0],
                    maxHeightUpdated
                );

                // store result in cache
                memo[i][remainingShelfWidth] = Math.min(
                    option1Height,
                    option2Height
                );

                return memo[i][remainingShelfWidth];
            } 

            // store result in cache
            memo[i][remainingShelfWidth] = option1Height;
            return memo[i][remainingShelfWidth];
            
        }
    }
}


// class Solution {
//     public int minHeightShelves(int[][] books, int shelfWidth) {
//         int[] dp = new int[books.length + 1];
//         dp[books.length] = 0;
//         for (int i = books.length - 1; i >= 0; i--) {
//             int height = books[i][1];
//             int width = books[i][0];
//             dp[i] = height + dp[i + 1];
//             for (int j = i + 1; j < books.length && (width+books[j][0] <= shelfWidth); j++) {
//                 height = Math.max(height, books[j][1]);
//                 width += books[j][0];
//                 dp[i] = Math.min(dp[i], height + dp[j+1]);
//             }
//         }
//         return dp[0];
//     }
// }