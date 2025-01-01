package com.thealgorithms.gemastik.prep.A;

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

        int n = in.nextInt();
        int[] volumes = new int[n];

        for (int i = 0; i < n; i++) {
            volumes[i] = in.nextInt();
        }

        boolean allEqual = true;
        for (int i = 1; i < n; i++) {
            if (volumes[i] != volumes[0]) {
                allEqual = false;
                break;
            }
        }

        if (allEqual) {
            out.println("SESUAI");
            out.flush();
            return;
        }

        int totalVolume = 0;
        for (int volume : volumes) {
            totalVolume += volume;
        }

        if (totalVolume % n != 0) {
            out.println("TIDAK SESUAI");
            out.flush();
            return;
        }

        int targetVolume = totalVolume / n;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j) {
                    int diff = Math.abs(targetVolume - volumes[i]);
                    if (volumes[i] > targetVolume &&
                        volumes[j] < targetVolume && 
                        volumes[i] - targetVolume == targetVolume - volumes[j]) {
                        out.println(diff + " " + (i + 1) + " " + (j + 1));
                        out.flush();
                        return;
                    }
                }
            }
        }

        out.println("TIDAK SESUAI");

        out.flush();
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
