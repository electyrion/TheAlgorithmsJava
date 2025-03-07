package com.thealgorithms.leetcode;

import java.util.Arrays;

public class ClosestPrimeNumbersInRange {
    public int[] closestPrimes(int left, int right) {
        // get all primes up to right
        boolean[] isPrime = sieveOfEratosthenes(right);
        
        // first two primes in range
        int first = -1;
        int second = -1;
        
        int minDiff = Integer.MAX_VALUE;
        int prev = -1;
        
        for (int i = Math.max(2, left); i <= right; i++) {
            if (isPrime[i]) {
                if (prev == -1) {
                    prev = i;
                } else {
                    int diff = i - prev;
                    if (diff < minDiff) {
                        minDiff = diff;
                        first = prev;
                        second = i;
                    }
                    prev = i;
                }
            }
        }
        
        return first == -1 ? new int[]{-1, -1} : new int[]{first, second};
    }

    public boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        // check for prime by testing divisors up to square root of n, can skip even numbers
        // and optimize by checking only numbers of form 6k + 1
        for (int i = 5; i * i <= n; i+= 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
    
    // helper method to find prime numbers using sieve of eratosthenes
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}

// https://leetcode.com/problems/closest-prime-numbers-in-range/description/
