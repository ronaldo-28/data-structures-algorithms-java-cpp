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
    public ListNode removeNodes(ListNode head) {
        if(head == null && head.next == null){
            return head;
        }
        ListNode prev = null, curr = head;
        while(curr!=null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr = prev.next;
        prev.next = null;
        while(curr!=null){
            ListNode temp = curr.next;
            if(curr.val>=prev.val){
                curr.next = prev;
                prev = curr;
            }
            curr = temp;
        }
        return prev;
    }
}