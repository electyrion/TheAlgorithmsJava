package com.thealgorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/*
 * https://leetcode.com/problems/second-minimum-time-to-reach-destination/editorial
 */
public class SecondMinimumTimeToReachDestination {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            adj.computeIfAbsent(a, value -> new ArrayList<>()).add(b);
            adj.computeIfAbsent(b, value -> new ArrayList<>()).add(a);
        }
        int[] dist1 = new int[n+1], dist2 = new int[n+1], freq = new int[n+1];
        // dist1 stores the minimum time taken to reach node i from node 1.
        // dist2 stores the second minimum time taken to reach node from node 1.
        // freq[i] stores the number of time a node is popped out ouf the heap.
        for (int i = 1; i <= n; i++) {
            dist1[i] = dist2[i] = Integer.MAX_VALUE;
            freq[i] = 0;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {1, 0});
        dist1[1] = 0;

        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int node = temp[0];
            int timeTaken = temp[1];

            freq[node]++;

            // if the node is being visited for the second time and is 'n', return the answer
            if (freq[node] == 2 && node == n) {
                return timeTaken;
            }

            // if the current light is red, wait till the path turns green
            if ((timeTaken / change) % 2 == 1) {
                timeTaken = change * (timeTaken / change + 1) + time;
            } else {
                timeTaken = timeTaken + time;
            }

            if (!adj.containsKey(node)) continue;
            for (int neighbor : adj.get(node)) {
                // ignore nodes that have already popped out twice, we are not interested in visited again
                if (freq[neighbor] == 2) continue;

                // update dist1 if its more than the current timeTaken and
                // store its value in dist2 since that becomes the second min
                // value now
                if (dist1[neighbor] > timeTaken) {
                    dist2[neighbor] = dist1[neighbor];
                    dist1[neighbor] = timeTaken;
                    pq.offer(new int[] { neighbor, timeTaken });
                } else if (
                    dist2[neighbor] > timeTaken && 
                    dist1[neighbor] != timeTaken) {
                        dist2[neighbor] = timeTaken;
                        pq.offer(new int[] {neighbor, timeTaken});
                }
            }
        }
        return 0;
    }
}
