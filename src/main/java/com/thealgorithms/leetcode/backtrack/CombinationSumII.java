package com.thealgorithms.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    private void backtrack(
        List<List<Integer>> answer,
        List<Integer> tempList,
        int[] candidates,
        int totalLeft,
        int index
    ) {
        if (totalLeft < 0) return;
        else if (totalLeft == 0) {
            // add to the answer array, if the sum is equal to arget.
            answer.add(new ArrayList<>(tempList));
        } else {
            for (
                int i = index;
                i < candidates.length && totalLeft >= candidates[i];
                i++
            ) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                // add it to tempList
                tempList.add(candidates[i]);
                // check for all possible scenarios.
                backtrack(
                    answer,
                    tempList,
                    candidates,
                    totalLeft - candidates[i],
                    i + 1
                );
                // backtrack the tempList
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
