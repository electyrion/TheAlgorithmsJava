package com.thealgorithms.leetcode.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class NumberOfAtoms {
    public String countOfAtoms(String formula) {
        // Stack to keep track of the atoms and their counts
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());

        // Index to keep track of the current character
        int index = 0;
        int len = formula.length();

        // Parse the formula
        while (index < len) {
            char ch = formula.charAt(index);

            switch (ch) {
                case '(' -> {
                    stack.push(new HashMap<>());
                    index++;
                }
                case ')' -> {
                    Map<String, Integer> top = stack.pop();
                    index++;
                    int multiplicity = 0;
                    while (index < len && Character.isDigit(formula.charAt(index))) {
                        multiplicity = multiplicity * 10 + (formula.charAt(index) - '0');
                        index++;
                    }
                    
                    multiplicity = (multiplicity == 0) ? 1 : multiplicity;
                    Map<String, Integer> peek = stack.peek();
                    for (String atom : top.keySet()) {
                        peek.put(atom, peek.getOrDefault(atom, 0) + top.get(atom) * multiplicity);
                    }
                }
                default -> {
                    int start = index;
                    index++;
                    while (index < len && Character.isLowerCase(formula.charAt(index))) {
                        index++;
                    }
                    
                    String atom = formula.substring(start, index);
                    int multiplicity = 0;
                    while (index < len && Character.isDigit(formula.charAt(index))) {
                        multiplicity = multiplicity * 10 + (formula.charAt(index) - '0');
                        index++;
                    }
                    
                    multiplicity = (multiplicity == 0) ? 1 : multiplicity;
                    Map<String, Integer> peek = stack.peek();
                    peek.put(atom, peek.getOrDefault(atom, 0) + multiplicity);
                }
            }
        }

        // Sort the final map
        TreeMap<String, Integer> finalMap = new TreeMap<>(stack.pop());

        // Generate the answer string
        StringBuilder ans = new StringBuilder();
        for (Map.Entry<String, Integer> entry : finalMap.entrySet()) {
            ans.append(entry.getKey());
            if (entry.getValue() > 1) {
                ans.append(entry.getValue());
            }
        }

        return ans.toString();
    }

    public String countOfAtoms3(String formula) {
        // Stack to keep track of the atoms and their counts
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());

        // Index to keep track of the current character
        int index = 0;

        // Parse the formula
        while (index < formula.length()) {
            switch (formula.charAt(index)) {
                // If left parenthesis, insert a new hashmap to the stack. It will
                // keep track of the atoms and their counts in the nested formula
                case '(' -> {
                    stack.push(new HashMap<>());
                    index++;
                }

                // If right parenthesis, pop the top element from the stack
                // Multiply the count with the multiplicity of the nested formula
                case ')' -> {
                    Map<String, Integer> currMap = stack.pop();
                    index++;
                    StringBuilder multiplier = new StringBuilder();
                    while (
                        index < formula.length() &&
                        Character.isDigit(formula.charAt(index))
                    ) {
                        multiplier.append(formula.charAt(index));
                        index++;
                    }   
                    
                    if (multiplier.length() > 0) {
                        int mult = Integer.parseInt(multiplier.toString());
                        for (String atom : currMap.keySet()) {
                            currMap.put(atom, currMap.get(atom) * mult);
                        }
                    }
                    
                    for (String atom : currMap.keySet()) {
                        stack
                            .peek()
                            .put(
                                atom,
                                stack.peek().getOrDefault(atom, 0) +
                                currMap.get(atom)
                            );
                    }
                }

                // Otherwise, it must be a UPPERCASE LETTER. Extract the complete
                // atom with frequency, and update the most recent hashmap
                default -> {
                    StringBuilder currAtom = new StringBuilder();
                    currAtom.append(formula.charAt(index));
                    index++;
                    while (
                        index < formula.length() &&
                        Character.isLowerCase(formula.charAt(index))
                    ) {
                        currAtom.append(formula.charAt(index));
                        index++;
                    }
                    
                    StringBuilder currCount = new StringBuilder();
                    while (
                        index < formula.length() &&
                        Character.isDigit(formula.charAt(index))
                    ) {
                        currCount.append(formula.charAt(index));
                        index++;
                    }   

                    int count = currCount.length() > 0
                        ? Integer.parseInt(currCount.toString())
                        : 1;
                    stack
                        .peek()
                        .put(
                            currAtom.toString(),
                            stack.peek()
                                .getOrDefault(currAtom.toString(), 0) +
                                count
                        );
                }
            }
        }

        // Sort the final map
        TreeMap<String, Integer> finalMap = new TreeMap<>(stack.peek());

        // Generate the answer string
        StringBuilder ans = new StringBuilder();
        for (String atom : finalMap.keySet()) {
            ans.append(atom);
            if (finalMap.get(atom) > 1) {
                ans.append(finalMap.get(atom));
            }
        }

        return ans.toString();
    }

    public String countOfAtoms2(String formula) {
        // initialize a stack to store the number of atoms
        Stack<Map<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());

        // index to keep track of the current character
        int i = 0;
        int n = formula.length();
        while (i < n) {
            char c = formula.charAt(i);
            switch (c) {
                // if left parentheses, insert a new hashmap to the stack. it will keep track
                // of the atoms and their counts in the nested formula
                case '(' -> {
                    stack.push(new HashMap<>());
                    i++;
                }

                // if right parentheses, pop the top elements from the stack
                // multiply the count with the multiplicity of the nested formula
                case ')' -> {
                    Map<String, Integer> top = stack.pop();
                    i++;
                    int j = i;
                    while (j < n && Character.isDigit(formula.charAt(j))) {
                        j++;
                    }
                    
                    int count = j > i ? Integer.parseInt(formula.substring(i, j)) : 1;
                    for (String atom : top.keySet()) {
                        stack.peek()
                            .put(atom, 
                                stack.peek().getOrDefault(atom, 0) +
                                                        top.get(atom) * count);
                    }
                    i = j;
                }

                default -> {
                    int j = i + 1;
                    while (j < n && Character.isLowerCase(formula.charAt(j))) {
                        j++;
                    }
                    
                    String atom = formula.substring(i, j);
                    i = j;
                    while (j < n && Character.isDigit(formula.charAt(j))) {
                        j++;
                    }
                    
                    int count = j > i ? Integer.parseInt(formula.substring(i, j)) : 1;
                    stack.peek().put(atom, stack.peek().getOrDefault(atom, 0) + count);
                    i = j;
                }
            }
        }

        Map<String, Integer> map = stack.pop();
        List<String> atoms = new ArrayList<>(map.keySet());
        Collections.sort(atoms);
        StringBuilder sb = new StringBuilder();
        for (String atom : atoms) {
            sb.append(atom);
            if (map.get(atom) > 1) {
                sb.append(map.get(atom));
            }
        }

        return sb.toString();
    }
}
