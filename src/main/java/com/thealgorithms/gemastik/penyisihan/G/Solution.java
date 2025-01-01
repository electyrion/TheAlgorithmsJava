package com.thealgorithms.gemastik.penyisihan.G;

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

        int[] B = new int[N];
        int[] J = new int[N];

        for (int i = 0; i < N; i++) {
            B[i] = in.nextInt();
        }

        for (int i = 0; i < N; i++) {
            J[i] = in.nextInt();
        }

        int maxProfit = 0;
        int minBuyPrice = Integer.MAX_VALUE;

        for (int j = 2; j < N; j++) {
            minBuyPrice = Math.min(minBuyPrice, B[j - 2]);
            int profit = J[j] - minBuyPrice;
            maxProfit = Math.max(maxProfit, profit);
        }

        if (maxProfit > 0) {
            out.println(maxProfit);
        } else {
            out.println("tidak mungkin");
        }

        out.close();
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
