package com.thealgorithms.leetcode.tree;

public class NumberOfGoodLeafNodesPairs {
    public int countPairs(TreeNode root, int distance) {
        return postOrder(root, distance)[11];
    }

    private int[] postOrder(TreeNode currentNode, int distance) {
        if (currentNode == null) return new int[12];
        else if (currentNode.left == null && currentNode.right == null) {
            int[] current = new int[12];

            // leaf node's distance from itself is 0
            current[0] = 1;
            return current;
        }

        // leaf node count for a given distance
        int[] left = postOrder(currentNode.left, distance);
        int[] right = postOrder(currentNode.right, distance);

        int[] current = new int[12];

        // combine the counts from the left and right subtree and shift by
        // +1 distance
        for (int i = 0; i < 10; i++) {
            current[i + 1] += left[i] + right[i];
        }

        // initialize to total number of good leaf nodes pairs from left
        // and right subtrees
        current[11] += left[11] + right[11];

        // Iterate through possible leaf node distance pairs
        for(int d1 = 0; d1 <= distance; d1++) {
            for (int d2 = 0; d2 <= distance; d2++) {
                if (2 + d1 + d2 <= distance) {
                    // if the total path distance is less than the given 
                    // distance then add to the total number of good pairs
                    current[11] += left[d1] * right[d2];
                }
            }
        }

        return current;
    }
}

/*
 * Algorithm
 * 
 * 1. Define postOrder(TreeNode currentNode, int distance) helper function. This function will return an array that contains the count of leaf nodes for all possible distances from currentNode (currentNode[0] to currentNode[10]), as well as the total number of good leaf nodes pairs rooted at currentNode (currentNode[11]).
 *      - If currentNode is null, then return an empty array with all 0s.
 *      - If currentNode is a leaf node, then return an array where the count for leaf nodes with distance 0 is set to 1.
 *      - Recursively call postOrder on the left subtree and store the result in the left array.
 *      - Recursively call postOrder on the right subtree and store the result in the right array.
 *      - Initialize a current array.
 *      - Shift the counts in left and right by 1 in current. Specifically, for each distance d:current[d+1] = left[d] + right[d].
 *      - Initialize current[11] to left[11] + right[11]. This is the total number of good leaf nodes pairs under the left and right subtrees.
 *      - For all distance pairs (d1, d2):
 *          - If 2 + d1 + d2 <= distance, then current[11] += left[d1] + right[d2].
 *      - Return current.
 * 
 * 2. Return postOrder(root, distance)[11], the total number of good leaf nodes pairs rooted at root.
 */