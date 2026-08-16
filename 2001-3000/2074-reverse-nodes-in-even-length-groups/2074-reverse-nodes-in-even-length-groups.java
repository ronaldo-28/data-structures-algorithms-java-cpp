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
    public ListNode reverseEvenLengthGroups(ListNode head) {
        // start by k being 1 because we are tracking n natural number
        // which goes like 1, 2, 3, 4...
        return recurse(head, 1);
    }

    public ListNode recurse(ListNode head, int k) {
        // base case
        if (head == null) {
            return head;
        }

        // checking for available nodes
        ListNode temp = head;
        int count = 1;
        while (temp.next != null && count < k) {
            temp = temp.next;
            count++;
        }

        // mark the upcoming group's head pointer
        ListNode nextHead = temp.next;

        // check for parity
        if (count % 2 == 0) {

            //reversal for even parity
            ListNode curr = head;
            ListNode prev = null;
            for (int i = 0; i < count; i++) {
                ListNode adv = curr.next;
                curr.next = prev;
                prev = curr;
                curr = adv;
            }

            // recursion for the nextNode 
            // also incrementing k by 1 for the next group
            head.next = recurse(nextHead, k + 1);
            // always return the head of the group 
            // which is prev in a reversed linked list
            return prev;
        }
        
        //odd parity
        temp.next = recurse(nextHead, k + 1);
        // always return the head of the group 
        // which is head itself in a normal linked list
        return head;
    }
}