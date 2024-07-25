package com.thealgorithms.leetcode.linkedlist;

public class MaximumTwinSumOfALinkedList {
    public int pairSum(ListNode head) {
        // find the middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the linked list mid -> end
        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        // find the maxPairSum
        int maxSum = Integer.MIN_VALUE;
        while (prev != null) {
            maxSum = Math.max(maxSum, head.val + prev.val);
            head = head.next;
            prev = prev.next;
        }

        return maxSum;
    }
}
