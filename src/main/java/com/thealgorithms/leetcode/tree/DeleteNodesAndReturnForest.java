package com.thealgorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for (int val : to_delete) {
            toDeleteSet.add(val);
        }
        List<TreeNode> forest = new ArrayList<>();
        
        root = processNode(root, toDeleteSet, forest);
        
        // if the root is not deleted, add it to the forest
        if (root != null) forest.add(root);
        
        return forest;
    }
    
    private TreeNode processNode(
        TreeNode node,
        Set<Integer> toDeleteSet,
        List<TreeNode> forest
        ) {
            if (node == null) return node;
            
            node.left = processNode(node.left, toDeleteSet, forest);
            node.right = processNode(node.right, toDeleteSet, forest);
            
            // node evaluation: check if the current node needs to be deleted
            if (toDeleteSet.contains(node.val)) {
                // if the node has left or right children, add them to the forest
                if (node.left != null) forest.add(node.left);
                if (node.right != null) forest.add(node.right);
                
                // return null to its parent to delete the current node
                return null;
            }
            
            return node;
        }
    }
    
// https://leetcode.com/problems/delete-nodes-and-return-forest/?envType=daily-question&envId=2024-07-17
/*
 * We mentioned the need to process each node's children before the node itself. One traversal method that aligns with this
 * requirement is postorder traversal. In postorder traversal, we visit the left child, then the right child, and finally 
 * the parent node. This sequence ensures that by the time we reach a node, its entire subtree has already been processed, 
 * allowing us to safely delete the node if necessary.
 * 
 * In contrast, preorder and inorder traversals do not meet this requirement. In preorder traversal, we visit the parent node
 * before its children, risking deletion of a node before its children are handled, potentially losing subtrees. In inorder
 * traversal, we first visit the left child, then the parent node, and finally the right child, partially processing the subtree
 * before addressing the parent node, which can lead to incomplete handling of nodes and subtree loss.
 * To solve this problem, we recursively traverse each node's left and right children before processing the node itself. If the
 * current node needs deletion, we check its children. If they are not null, we add them to the forest as new roots. Finally, we
 * delete the current node by returning null to its parent.
 * 
 * Edge Case: Special handling is required for the root node. After processing the entire tree, if the root is not null and hasn't
 * been deleted, it should be added to the forest as a remaining root
 */



// Approach 2:  BFS Forest Formation
// class Solution {

//     public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
//         if (root == null) {
//             return new ArrayList<>();
//         }

//         Set<Integer> toDeleteSet = new HashSet<>();
//         for (int val : to_delete) {
//             toDeleteSet.add(val);
//         }

//         List<TreeNode> forest = new ArrayList<>();

//         Queue<TreeNode> nodesQueue = new LinkedList<>();
//         nodesQueue.add(root);

//         while (!nodesQueue.isEmpty()) {
//             TreeNode currentNode = nodesQueue.poll();

//             if (currentNode.left != null) {
//                 nodesQueue.add(currentNode.left);
//                 // Disconnect the left child if it needs to be deleted
//                 if (toDeleteSet.contains(currentNode.left.val)) {
//                     currentNode.left = null;
//                 }
//             }

//             if (currentNode.right != null) {
//                 nodesQueue.add(currentNode.right);
//                 // Disconnect the right child if it needs to be deleted
//                 if (toDeleteSet.contains(currentNode.right.val)) {
//                     currentNode.right = null;
//                 }
//             }

//             // If the current node needs to be deleted, add its non-null children to the forest
//             if (toDeleteSet.contains(currentNode.val)) {
//                 if (currentNode.left != null) {
//                     forest.add(currentNode.left);
//                 }
//                 if (currentNode.right != null) {
//                     forest.add(currentNode.right);
//                 }
//             }
//         }

//         // Ensure the root is added to the forest if it is not to be deleted
//         if (!toDeleteSet.contains(root.val)) {
//             forest.add(root);
//         }

//         return forest;
//     }
// }

/*
 * In the previous approach, we recursively traversed the nodes of the binary tree root using the postorder traversal algorithm.
 * An alternative is applying an iterative approach, using a queue for breadth-first search (BFS). This allows us to process each
 * node level by level. Starting with the root node in the queue, we handle each node and its children iteratively, disconnecting
 * nodes marked for deletion and adding any remaining nodes to the forest.
 * 
 * BFS explores all nodes at the current depth before progressing to nodes at deeper levels. We use a queue for BFS to manage
 * traversal order, ensuring nodes are visited level by level.
 * 
 * Starting BFS with the root node, we systematically process the tree from the top down. As each node is processed, we assess if
 * it needs deletion. If so, we disconnect it from its parent and potentially treat its children as new roots for the forest by
 * enqueuing them.
 * 
 * We have to make sure we are not losing any nodes in the subtree while disconnecting a node, by pushing its children to the
 * queue before deleting that node. This way, the children can be handled as potential new roots for the forest.
 * 
 * If a node's children need to be deleted, we disconnect them as well. Finally, after processing all nodes, we check the root
 * node separately. If the root was not deleted, we will add it to the forest as well.
 */