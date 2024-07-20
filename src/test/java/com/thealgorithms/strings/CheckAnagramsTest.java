package com.thealgorithms.strings;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CheckAnagramsTest {
    private static final String MESSAGE = "Strings must contain only lowercase English letters!";

    // CHECK METHOD isAnagrams()
    @Test
    public void testCheckAnagrams() {
        String testString1 = "STUDY";
        String testString2 = "DUSTY";
        Assertions.assertTrue(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    @Test
    public void testCheckFalseAnagrams() {
        String testString1 = "STUDY";
        String testString2 = "random";
        Assertions.assertFalse(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    @Test
    public void testCheckSameWordAnagrams() {
        String testString1 = "STUDY";
        Assertions.assertTrue(CheckAnagrams.isAnagrams(testString1, testString1));
    }

    @Test
    public void testCheckDifferentCasesAnagram() {
        String testString1 = "STUDY";
        String testString2 = "dusty";
        Assertions.assertTrue(CheckAnagrams.isAnagrams(testString1, testString2));
    }

    // CHECK METHOD isAnagramsUnicode()
    // Below tests work with strings which consist of Unicode symbols & the algorithm is case-sensitive.
    @Test
    public void testStringAreValidAnagramsCaseSensitive() {
        Assertions.assertTrue(CheckAnagrams.isAnagramsUnicode("Silent", "liSten"));
        Assertions.assertTrue(CheckAnagrams.isAnagramsUnicode("This is a string", "is This a string"));
    }

    @Test
    public void testStringAreNotAnagramsCaseSensitive() {
        Assertions.assertFalse(CheckAnagrams.isAnagramsUnicode("Silent", "Listen"));
        Assertions.assertFalse(CheckAnagrams.isAnagramsUnicode("This is a string", "Is this a string"));
    }

    // CHECK METHOD isAnagramsOptimized()
    // Below tests work with strings which consist of only lowercase English letters
    @Test
    public void testOptimizedAlgorithmStringsAreValidAnagrams() {
        Assertions.assertTrue(CheckAnagrams.isAnagramsOptimized("silent", "listen"));
        Assertions.assertTrue(CheckAnagrams.isAnagramsOptimized("mam", "amm"));
    }

    @Test
    public void testOptimizedAlgorithmShouldThrowExceptionWhenStringsContainUppercaseLetters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> CheckAnagrams.isAnagramsOptimized("Silent", "Listen"));
        Assertions.assertEquals(MESSAGE, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> Assertions.assertFalse(CheckAnagrams.isAnagramsOptimized("This is a string", "Is this a string")));
        Assertions.assertEquals(MESSAGE, exception.getMessage());
    }
}
