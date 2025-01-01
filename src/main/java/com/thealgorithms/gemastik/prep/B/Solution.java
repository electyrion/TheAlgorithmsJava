package com.thealgorithms.gemastik.prep.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    private static InputReader in;
    private static PrintWriter out;

    private static final String[][] table1 = {
        {"AB", "DC", "CC", "CB"},
        {"CA", "DA", "CD", "DD"},
        {"BC", "AA", "BA", "DB"},
        {"BD", "AD", "BB", "AC"}
    };

    private static final String[][] table2 = {
        {"CD", "BB", "AC", "CC"},
        {"CB", "DB", "AD", "DD"},
        {"DA", "DC", "BC", "BD"},
        {"AA", "BA", "CA", "AB"}
    };

    private static Map<String, String> createReverseTable(String[][] table) {
        Map<String, String> reverseTable = new HashMap<>();
        char[] labels = {'A', 'B', 'C', 'D'};

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                String key = "" + labels[row] + labels[col];
                String value = "" + table[row][col];
                reverseTable.put(value, key);
            }
        }
        return reverseTable;
    }

    private static final Map<String, String> reverseTable1 = createReverseTable(table1);
    private static final Map<String, String> reverseTable2 = createReverseTable(table2);

    public static String decode(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        
        for (int i = 0; i < encodedMessage.length(); i += 2) {
            String encodedPair = encodedMessage.substring(i, i + 2);
            String intermediatePair = reverseTable2.get(encodedPair);
            String originalPair = reverseTable1.get(intermediatePair);
            decodedMessage.append(originalPair);
        }
        
        return decodedMessage.toString();
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);
        
        String encodedMessage = in.next();
        out.println(decode(encodedMessage));

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
