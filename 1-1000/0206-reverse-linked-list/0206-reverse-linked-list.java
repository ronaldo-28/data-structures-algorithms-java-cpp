/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // Iterative Solution
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode nextNode = null;

        while (curr != null) {
            nextNode = curr.next; // Store the next node
            curr.next = prev;     // Reverse the link
            prev = curr;         // Move 'prev' one step forward
            curr = nextNode;     // Move 'curr' one step forward
        }

        return prev; // 'prev' will be the new head of the reversed list
    }

    // Recursive Solution (for completeness and follow-up)
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head; // Base case: empty list or single-node list
        }

        ListNode newHead = reverseListRecursive(head.next); // Recursively reverse the rest of the list
        head.next.next = head; // Make the next node point back to the current node
        head.next = null;      // Set the current node's next to null (it's now the tail)

        return newHead; // The new head of the reversed sublist
    }
}