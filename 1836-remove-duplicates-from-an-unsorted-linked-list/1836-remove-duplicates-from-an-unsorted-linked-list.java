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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        int num[] = new int[100001];
        ListNode prev = new ListNode(-1);
        ListNode ans = prev;
        ListNode curr = head;

        while(curr!=null){
           num[curr.val]++;
           curr = curr.next;
        }
        
        ListNode curr1 = head;
        while(curr1!=null){
             if(num[curr1.val] == 1){
                prev.next = curr1;
                prev = curr1;
             }
             curr1 = curr1.next;             
        }
        prev.next = null;      
       return ans.next;
    }
}