package com.thealgorithms.leetcode.recursion;

import java.util.HashMap;
import java.util.Map;

public class IntegerToEnglishWords {
    private static final Map<Integer, String> map = new HashMap<>();
    
    static {
        map.put(0, "Zero");
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
        map.put(1000, "Thousand");
        map.put(1000000, "Million");
        map.put(1000000000, "Billion");
    }


    public String numberToWords(int num) {
        if (num == 0) return map.get(num);

        return convert(num).trim();
    }

    private String convert(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return map.get(num) + " ";
        } else if (num < 100) {
            return map.get(num / 10 * 10) + " " + convert(num % 10);
        } else if (num < 1000) {
            return map.get(num / 100) + " Hundred " + convert(num % 100);
        } else if (num < 1000000) {
            return convert(num / 1000) + "Thousand " + convert(num % 1000);
        } else if (num < 1000000000) {
            return convert(num / 1000000) + "Million " + convert(num % 1000000);
        } else {
            return convert(num / 1000000000) + "Billion " + convert(num % 1000000000);
        }
    }
}
