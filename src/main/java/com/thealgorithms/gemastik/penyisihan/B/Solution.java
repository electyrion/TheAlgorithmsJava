package com.thealgorithms.gemastik.penyisihan.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        long L = in.nextLong();
        
        int[] cuts = new int[N];
        for (int i = 0; i < N; i++) {
            cuts[i] = in.nextInt();
        }

        List<Long> parts = new ArrayList<>();
        parts.add(L);

        // perform each cut
        for (int i = 0; i < N; i++) {
            int cutIndex = cuts[i] - 1; // 1-based index to 0-based
            long partLength = parts.get(cutIndex);
            long newPartLength = partLength / 2;
            
            // replace the original part with two equal parts
            parts.set(cutIndex, newPartLength);
            parts.add(cutIndex + 1, newPartLength);
        }

        // print the lengths of the final parts
        for (long part : parts) {
            out.print(part + " ");
        }
        out.println();

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

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
