package com.thealgorithms.leetcode.tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    // driver method to test
    public static void main(String[] args) {
        
        // empty constructor
        TreeNode empty = new TreeNode();
        System.out.println(empty.getVal());
        
        // constructor with value
        TreeNode node = new TreeNode(10);
        System.out.println(node.getVal());
        
        // constructor with value and left and right nodes
        TreeNode root = new TreeNode(1, empty, node);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
    }
}
