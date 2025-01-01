package com.thealgorithms.gemastik.penyisihan.J;

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
        int[] heights = new int[N];
        
        for (int i = 0; i < N; i++) {
            heights[i] = in.nextInt();
        }
        
        // maximum difficulty for gema
        int[] sortedHeights = heights.clone();
        Arrays.sort(sortedHeights);
        int maxDifficultyGema = 0;
        for (int i = 1; i < N; i++) {
            maxDifficultyGema = Math.max(maxDifficultyGema, Math.abs(sortedHeights[i] - sortedHeights[i - 1]));
        }
        
        // maximum difficulty for astik
        int maxDifficultyAstik = sortedHeights[N - 1] - sortedHeights[0];
        
        out.println(maxDifficultyGema);
        out.println(maxDifficultyAstik);

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
