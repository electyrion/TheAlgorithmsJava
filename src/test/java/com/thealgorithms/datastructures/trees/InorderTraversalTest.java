package com.thealgorithms.datastructures.trees;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Albina Gimaletdinova on 21/02/2023
 */
public class InorderTraversalTest {
    @Test
    public void testNullRoot() {
        assertEquals(Collections.emptyList(), InorderTraversal.recursiveInOrder(null));
        assertEquals(Collections.emptyList(), InorderTraversal.iterativeInOrder(null));
    }

    /*
         1
        / \
       2   3
      /\   /\
     4  5 6  7
    */
    @Test
    public void testRecursiveInorder() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        List<Integer> expected = List.of(4, 2, 5, 1, 6, 3, 7);

        assertEquals(expected, InorderTraversal.recursiveInOrder(root));
        assertEquals(expected, InorderTraversal.iterativeInOrder(root));
    }

    /*
         5
          \
           6
            \
             7
              \
               8
    */
    @Test
    public void testRecursiveInorderNonBalanced() {
        final BinaryTree.Node root = TreeTestUtils.createTree(new Integer[] {5, null, 6, null, 7, null, 8});
        List<Integer> expected = List.of(5, 6, 7, 8);

        assertEquals(expected, InorderTraversal.recursiveInOrder(root));
        assertEquals(expected, InorderTraversal.iterativeInOrder(root));
    }
}
