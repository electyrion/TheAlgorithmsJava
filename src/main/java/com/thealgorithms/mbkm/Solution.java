package com.thealgorithms.mbkm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    private static InputReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int M = in.nextInt();
        int S = in.nextInt();
        String player1Ships = in.next();
        String player2Ships = in.next();
        int T = in.nextInt();
        String player1Moves = in.next();
        String player2Moves = in.next();

        Set<String> player1ShipSet = new HashSet<>();
        Set<String> player2ShipSet = new HashSet<>();

        // parse player 1 ship
        String[] p1Ships = player1Ships.split(":");
        player1ShipSet.addAll(Arrays.asList(p1Ships));

        // parse player 2 ship
        String[] p2Ships = player2Ships.split(":");
        player2ShipSet.addAll(Arrays.asList(p2Ships));

        int player1Hits = 0;
        int player2Hits = 0;


        // parse player 1 moves
        String[] p1Moves = player1Moves.split(":");
        for (int i = 0; i < T; i++) {
            if (player2ShipSet.contains(p1Moves[i])) {
                player1Hits++;
                player2ShipSet.remove(p1Moves[i]);
            } 
        }

        // parse player 2 moves
        String[] p2Moves = player2Moves.split(":");
        for (int i = 0; i < T; i++) {
            if (player1ShipSet.contains(p2Moves[i])) {
                player2Hits++;
                player1ShipSet.remove(p2Moves[i]);
            }
        }

        if (player1Hits > player2Hits) {
            out.println("Playe 1 wins");
        } else if (player2Hits > player1Hits) {
            out.println("Player 2 wins");
        } else {
            out.println("Draw");
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
