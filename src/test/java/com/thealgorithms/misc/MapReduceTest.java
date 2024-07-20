package com.thealgorithms.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MapReduceTest {
    @Test
    public void testMapReduceWithSingleWordSentence() {
        String oneWordSentence = "Hactober";
        String result = MapReduce.mapReduce(oneWordSentence);

        assertEquals("Hactober: 1", result);
    }

    @Test
    public void testMapReduceWithMultipleWordSentence() {
        String multipleWordSentence = "I Love Love HactoberFest";
        String result = MapReduce.mapReduce(multipleWordSentence);

        assertEquals("I: 1,Love: 2,HactoberFest: 1", result);
    }
}
