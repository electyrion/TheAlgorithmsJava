package com.thealgorithms.gemastik.penyisihan.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int Q = in.nextInt();

        int[] prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = in.nextInt();
        }

        int[] queries = new int[Q];
        for (int i = 0; i < Q; i++) {
            queries[i] = in.nextInt();
        }

        Arrays.sort(prices);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int Xi = queries[i];
            long totalCost = calculateMinCost(prices, N, Xi);
            sb.append(totalCost).append("\n");
        }

        out.print(sb.toString());
        out.close();
    }

    public static long calculateMinCost(int[] prices, int N, int Xi) {
        long totalCost = 0;

        for (int i = 0; i < N; i += Xi) {
            int transactionSize = Math.min(Xi, N - i);
            int minPrice = prices[i];

            for (int j = 0; j < transactionSize; j++) {
                totalCost += prices[i + j];
                if (prices[i + j] < minPrice) {
                    minPrice = prices[i + j];
                }
            }

            totalCost -= minPrice / 2;
        }

        return totalCost;
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
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
