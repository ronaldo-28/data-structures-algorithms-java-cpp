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
    public ListNode[] splitCircularLinkedList(ListNode list) {
        
        ListNode slow = list;
        ListNode fast = list.next;
        
        while(fast!=list && fast.next!=list){
             
              slow = slow.next;
              fast = fast.next;

              if(fast.next!=list){
             fast = fast.next;
           }
        }
          
          fast.next = slow.next;
          slow.next = list;

          return new ListNode[]{list, fast.next};
    }
}