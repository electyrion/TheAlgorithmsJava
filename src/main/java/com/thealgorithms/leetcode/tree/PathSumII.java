package com.thealgorithms.leetcode.tree;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return new AbstractList<List<Integer>>() {

            private List<List<Integer>> resList;

            private void onload() {
                resList = new ArrayList<>();
                if (null == root) {
                    return;
                }
                ArrayList<Integer> path = new ArrayList<>();
                path.add(root.val);

                dfs(root, path, root.val, targetSum);
            }

            private void dfs(TreeNode root, ArrayList<Integer> path, int sum, int target) {
                    //meet leaf
                    if (root.left == null && root.right == null) {
                        if (sum == target) {
                            resList.add(new ArrayList<>(path));
                        }
                        return;
                    }

                    //go left
                    if (root.left != null) {
                        path.add(root.left.val);
                        dfs(root.left, path, sum + root.left.val, target);
                        path.remove(path.size() - 1);
                    }

                    //go right
                    if (root.right != null) {
                        path.add(root.right.val);
                        dfs(root.right, path, sum + root.right.val, target);
                        path.remove(path.size() - 1);
                    }
                }

            private void init() {
                if (null == resList) {
                    onload();
                    System.gc();
                }
            }

            @Override
            public List<Integer> get(int index) {
                init();
                return resList.get(index);
            }

            @Override
            public int size() {
                init();
                return resList.size();
            }

        };
    }
}
