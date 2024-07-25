package com.thealgorithms.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> dire = new LinkedList<>();
        Queue<Integer> radiant = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'D') {
                dire.add(i);
            } else {
                radiant.add(i);
            }
        }

        while (!dire.isEmpty() && !radiant.isEmpty()) {
            int dIndex = dire.poll();
            int rIndex = radiant.poll();

            if (dIndex < rIndex) {
                dire.add(dIndex + n);
            } else {
                radiant.add(rIndex + n);
            }
        }

        return dire.isEmpty() ? "Radiant" : "Dire";
    }
}
