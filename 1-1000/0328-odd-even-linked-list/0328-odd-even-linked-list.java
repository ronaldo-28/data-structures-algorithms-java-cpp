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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // Empty or single node list, no changes needed
        }

        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddTail = oddHead;
        ListNode evenTail = evenHead;
        ListNode current = evenHead.next;
        boolean isOdd = true;

        while (current != null) {
            if (isOdd) {
               oddTail.next = current;
               oddTail = current;
            } else {
              evenTail.next = current;
              evenTail = current;
            }
            isOdd = !isOdd;
            current = current.next;
        }
        evenTail.next = null;
        oddTail.next = evenHead;
        
        return oddHead;
    }
}