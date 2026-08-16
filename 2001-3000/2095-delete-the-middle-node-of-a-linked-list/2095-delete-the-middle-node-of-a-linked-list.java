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
    public ListNode deleteMiddle(ListNode head) {
        ListNode ptr = head;
        ListNode prev = head;

        if(head.next==null){
            return null;
        }

        if(head.next.next==null){
            head.next = null;
            return head;
        }

        while( ptr!=null && ptr.next!=null ){
            ptr = ptr.next.next;
            prev = prev.next;
        }
        
        prev.val = prev.next.val;
        prev.next = prev.next.next;

        return head;
    }
}