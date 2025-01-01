package com.thealgorithms.gemastik.penyisihan.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    private static InputReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N = in.nextInt();
        int M = in.nextInt();

        if (N <= 0 || M < 0) {
            throw new IllegalArgumentException("Invalid values for N or M");
        }

        DSU dsu = new DSU(N);

        // read the connections and union the sets
        for (int i = 0; i < M; i++) {
            int X = in.nextInt();
            int Y = in.nextInt();
            if (X < 1 || X > N || Y < 1 || Y > N) {
                throw new IllegalArgumentException("Input values out of range");
            }
            dsu.union(X - 1, Y - 1); // convert to 0-indexed
        }

        // count the number of connected components
        int[] componentSize = new int[N];
        for (int i = 0; i < N; i++) {
            int root = dsu.find(i);
            componentSize[root]++;
        }

        int vehiclesNeeded = 0;
        int singletonsCount = 0;

        for (int i = 0; i < N; i++) {
            if (componentSize[i] > 1) {
                vehiclesNeeded++;
            } else if (componentSize[i] == 1) {
                singletonsCount++;
            }
        }

        if (singletonsCount == 1 && vehiclesNeeded == 0) {
            vehiclesNeeded++;
        } else if (singletonsCount > 1) {
            vehiclesNeeded++;
        }

        out.println(vehiclesNeeded);
        out.close();
    }

    static class DSU {
        private final int[] parent;
        private final int[] rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int u) {
            if (u != parent[u]) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                if (rank[rootU] > rank[rootV]) {
                    parent[rootV] = rootU;
                } else if (rank[rootU] < rank[rootV]) {
                    parent[rootU] = rootV;
                } else {
                    parent[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            if (stream == null) {
                throw new IllegalArgumentException("Input stream cannot be null");
            }
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        throw new RuntimeException("End of input");
                    }
                    tokenizer = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
