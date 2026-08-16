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
    public ListNode reverseKGroup(ListNode head, int k) {
        
        ListNode curr = head;
        ListNode newHead = null, kTail = null;

        while (curr != null) {
            int count = 0;

            while (count < k && curr != null) {
                curr = curr.next;
                count++;
            }

            if (count == k) {
                ListNode reverseHead = this.reverse(head, count);

                if (newHead == null) {
                    newHead = reverseHead;
                }

                if (kTail != null) {
                    kTail.next = reverseHead;
                }
                kTail = head;
                head = curr;
            }

            if (kTail != null) {
                kTail.next = head;
            }
        }

        return newHead != null ? newHead : head;
    }

    private ListNode reverse(ListNode node, int count) {
        ListNode prev = null;

        while (count > 0) {
            ListNode temp = node.next;
            node.next = prev;
            prev = node;
            node = temp;
            count--;
        }

        return prev;
    }
}