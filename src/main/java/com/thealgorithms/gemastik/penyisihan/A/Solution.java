package com.thealgorithms.gemastik.penyisihan.A;

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

        int A = in.nextInt(); // daging
        int B = in.nextInt(); // ayam
        int C = in.nextInt(); // udang
        int D = in.nextInt(); // ikan


        int daysForDaging = (A + 2) / 3; // Days needed to finish lumpia daging
        int daysForAyam = (B + 2) / 3;   // Days needed to finish lumpia ayam
        int daysForUdang = (C + 2) / 3;  // Days needed to finish lumpia udang
        int daysForIkan = (D + 2) / 3;   


        int totalDays = daysForDaging;

        totalDays = Math.max(totalDays, daysForDaging + (daysForAyam - 1));
        totalDays = Math.max(totalDays, daysForDaging + daysForAyam + (daysForUdang - 1));
        totalDays = Math.max(totalDays, daysForDaging + daysForAyam + daysForUdang + (daysForIkan - 1));

        out.println(totalDays);
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
