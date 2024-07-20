package com.thealgorithms.others;

import java.util.Scanner;

/**
 * Fibonacci sequence, and characterized by the fact that every number after the
 * first two is the sum of the two preceding ones.
 *
 * <p>
 * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21,...
 *
 * <p>
 * Source for the explanation: https://en.wikipedia.org/wiki/Fibonacci_number
 *
 * Problem Statement: print all Fibonacci numbers that are smaller than your
 * given input N
 */
public final class FibonacciSeries {
    private FibonacciSeries() {
    }

    public static void main(String[] args) {
        int n;
        int first;
        int second;
        try ( // Get input from the user
                Scanner scan = new Scanner(System.in)) {
            n = scan.nextInt();
            first = 0;
            second = 1;
        }
        while (first <= n) {
            // print first fibo 0 then add second fibo into it while updating second as well
            System.out.println(first);
            int next = first + second;
            first = second;
            second = next;
        }
    }
}
