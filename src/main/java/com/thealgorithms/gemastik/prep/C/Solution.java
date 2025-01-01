package com.thealgorithms.gemastik.prep.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            numbers.add(in.nextInt());
        }

        // remove duplicates -> sort numbers
        Collections.sort(numbers);
        List<Integer> uniqueNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0 || !numbers.get(i).equals(numbers.get(i - 1))) {
                uniqueNumbers.add(numbers.get(i));
            }
        }

        // summarize
        StringBuilder summary = new StringBuilder();
        int start = uniqueNumbers.get(0);
        int end = start;

        for (int i = 1; i < uniqueNumbers.size(); i++) {
            int current = uniqueNumbers.get(i);
            if (current == end + 1) {
                end = current;
            } else {
                if (summary.length() > 0) {
                    summary.append(",");
                }
                if (start == end) {
                    summary.append(start);
                } else {
                    summary.append(start).append("-").append(end);
                }
                start = current;
                end = start;
            }
        }

        if (summary.length() > 0) {
            summary.append(",");
        }
        if (start == end) {
            summary.append(start);
        } else {
            summary.append(start).append("-").append(end);
        }

        out.println(summary.toString());


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
