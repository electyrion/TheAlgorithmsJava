package com.thealgorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


public class MinimumCostToConvertStringI {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // create a graph representation of character conversions
        @SuppressWarnings("unchecked")
        List<int[]>[] adj = new List[26]; // why not using int[26] ??
        for (int i = 0; i < 26; i++) {
            adj[i] = new ArrayList<>();
        }

        // populate the adjacency list with character conversions
        int conversionCount = original.length;
        for (int i = 0; i < conversionCount; i++) {
            // - 'a' is used to convert a character to its corresponding integer index
            adj[original[i] - 'a'].add(
                new int[] { changed[i] - 'a', cost[i] }
            ); 
        }

        // calculate shortest paths for all possible character conversions
        long[][] minConversionCosts = new long[26][26];
        for (int i = 0; i < 26; i++) {
            minConversionCosts[i] = dijkstra(i, adj);
        }

        // calculate the total cost of converting source to target
        long totalCost = 0;
        int stringLength = source.length();
        for (int i = 0; i < stringLength; i++) {
            if (source.charAt(i) != target.charAt(i)) {
                long charConversionCost = 
                    minConversionCosts[source.charAt(i) - 'a'][target.charAt(
                        i
                    ) - 'a'];
                if (charConversionCost == -1) {
                    return -1; // conversion not possible
                }
                totalCost += charConversionCost;
            }
        }
        return totalCost;
    }

    // find minimum conversion costs from a starting character to all other characters
    private long[] dijkstra(int startChar, List<int[]>[] adj) {
        // priority queue to store characters with their conversion cost, sorted by cost
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>(
            (e1, e2) -> Long.compare(e1.getKey(), e2.getKey())
        );

        // initialize the starting character with cost 0
        pq.add(new Pair<>(0L, startChar));

        // array to store the minimum conversion cost to each character
        long[] minCosts = new long[26];
        // initialize all costs to -1 (unreachable)
        Arrays.fill(minCosts, -1L);

        while (!pq.isEmpty()) {
            Pair<Long, Integer> currentPair = pq.poll();
            long currentCost = currentPair.getKey();
            int currentChar = currentPair.getValue();

            if (
                minCosts[currentChar] != -1L &&
                minCosts[currentChar] < currentCost
            ) continue;

            // explore all possible conversions from the current character
            for (int[] conversion : adj[currentChar]) {
                int targetChar = conversion[0];
                long conversionCost = conversion[1];
                long newTotalCost = currentCost + conversionCost;

                // if we found a cheaper conversion, update its cost
                if (
                    minCosts[targetChar] == -1L ||
                    newTotalCost < minCosts[targetChar]
                ) {
                    minCosts[targetChar] = newTotalCost;
                    // add the updated conversion to the queue for further exploration
                    pq.add(new Pair<>(newTotalCost, targetChar));
                }
            }
        }

        // return the array of minimum conversion costs from the starting character to all others
        return minCosts;
    }
}



// class Solution {
//     public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
//         int[][] dis = new int[26][26];
//         for (int i = 0; i < 26; i++) {
//             Arrays.fill(dis[i], Integer.MAX_VALUE);
//             dis[i][i] = 0;
//         }
//         for (int i = 0; i < cost.length; i++) {
//             dis[original[i] - 'a'][changed[i] - 'a'] = Math.min(dis[original[i] - 'a'][changed[i] - 'a'], cost[i]);
//         }
//         for (int k = 0; k < 26; k++) {
//             for (int i = 0; i < 26; i++)
//                 if (dis[i][k] < Integer.MAX_VALUE) {
//                     for (int j = 0; j < 26; j++) {
//                         if (dis[k][j] < Integer.MAX_VALUE) {
//                             dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
//                         }
//                     }
//                 }
//         }
//         long ans = 0L;
//         for (int i = 0; i < source.length(); i++) {
//             int c1 = source.charAt(i) - 'a';
//             int c2 = target.charAt(i) - 'a';
//             if (dis[c1][c2] == Integer.MAX_VALUE) {
//                 return -1L;
//             } else {
//                 ans += (long)dis[c1][c2];
//             }
//         }
//         return ans;
//     }
// }