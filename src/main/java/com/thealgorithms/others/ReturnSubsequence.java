package com.thealgorithms.others;

import java.util.Scanner;

public final class ReturnSubsequence {
    private ReturnSubsequence() {
    }

    public static void main(String[] args) {
        System.out.println("Enter String: ");
        try (Scanner s = new Scanner(System.in)) {
            String givenString = s.next(); // given string
            String[] subsequence = returnSubsequence(givenString); // calling returnSubsequence() function
            System.out.println("Subsequences : ");
            // print the given array of subsequences
            for (String subsequence1 : subsequence) {
                System.out.println(subsequence1);
            }
        } // given string
    }

    /**
     * @param givenString
     * @return subsequence
     */
    private static String[] returnSubsequence(String givenString) {
        if (givenString.length() == 0) { // in it // If string is empty we will create an array of
                                         // size=1 and insert "" (Empty string)
            String[] ans = new String[1];
            ans[0] = "";
            return ans;
        }
        String[] smallAns = returnSubsequence(givenString.substring(1)); // recursive call to get subsequences of substring starting from index
        // position=1

        String[] ans = new String[2 * smallAns.length]; // Our answer will be an array off string of size=2*smallAns
        System.arraycopy(smallAns, 0, ans, 0, smallAns.length);

        for (int k = 0; k < smallAns.length; k++) {
            ans[k + smallAns.length] = givenString.charAt(0) + smallAns[k]; // Insert character at index=0 of the given
                                                                            // substring in front of every string
            // in smallAns
        }
        return ans;
    }
}
