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
    public ListNode deleteNodes(ListNode head, int m, int n) {
        if(head == null) 
            return head;
        
        ListNode cur = head;
        int count = m-1;
        while(count-- > 0) {
            if(cur.next == null) 
                return head; 
            cur = cur.next;
        }

        //now pointing at the last node
        ListNode lastNodeMpart = cur;
        count = n;
        while(count-- > 0) {
            if(cur.next == null) 
                break; 
            cur = cur.next;
        }
  
        lastNodeMpart.next = cur.next;
        deleteNodes(lastNodeMpart.next, m, n);
        return head;        
    }
}