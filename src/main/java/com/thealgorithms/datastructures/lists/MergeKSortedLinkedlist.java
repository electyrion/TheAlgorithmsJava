package com.thealgorithms.datastructures.lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Arun Pandey (https://github.com/pandeyarun709)
 */
public class MergeKSortedLinkedlist {

    /**
     * This function merge K sorted LinkedList
     *
     * @param a array of LinkedList
     * @param n size of array
     * @return node
     */
    Node mergeKList(Node[] a, int n) {
        // Min Heap
        PriorityQueue<Node> min = new PriorityQueue<>(Comparator.comparingInt(x -> x.data));

        // adding head of all linkedList in min heap
        min.addAll(Arrays.asList(a).subList(0, n));

        // Make new head among smallest heads in K linkedList
        Node head = min.poll();
        min.add(head.next);
        Node curr = head;

        // merging LinkedList
        while (!min.isEmpty()) {
            Node temp = min.poll();
            curr.next = temp;
            curr = temp;

            // Add Node in min Heap only if temp.next is not null
            if (temp.next != null) {
                min.add(temp.next);
            }
        }

        return head;
    }

    private final class Node {

        private int data;
        private Node next;
    }

    // driver code
    public static void main(String[] args) {
        MergeKSortedLinkedlist mergeKSortedLinkedlist = new MergeKSortedLinkedlist();
        Node[] a = new Node[3];
        a[0] = mergeKSortedLinkedlist.new Node();
        a[0].data = 1;
        a[0].next = mergeKSortedLinkedlist.new Node();
        a[0].next.data = 3;
        a[0].next.next = mergeKSortedLinkedlist.new Node();
        a[0].next.next.data = 5;
        a[0].next.next.next = null;

        a[1] = mergeKSortedLinkedlist.new Node();
        a[1].data = 2;
        a[1].next = mergeKSortedLinkedlist.new Node();
        a[1].next.data = 4;
        a[1].next.next = mergeKSortedLinkedlist.new Node();
        a[1].next.next.data = 6;
        a[1].next.next.next = null;

        a[2] = mergeKSortedLinkedlist.new Node();
        a[2].data = 7;
        a[2].next = mergeKSortedLinkedlist.new Node();
        a[2].next.data = 8;
        a[2].next.next = mergeKSortedLinkedlist.new Node();
        a[2].next.next.data = 9;
        a[2].next.next.next = null;

        Node head = mergeKSortedLinkedlist.mergeKList(a, 3);

        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
}
