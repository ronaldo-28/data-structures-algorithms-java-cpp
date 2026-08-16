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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur=head;
        ListNode prev=null;
        while(cur!=null && cur.next!=null && cur.val==cur.next.val ){
            int value=cur.val;
            while(cur!=null && cur.val==value){
                cur=cur.next;
                head=cur;
            }
        }
        while(cur!=null&& cur.next!=null){
            if(cur.val==cur.next.val){
            int value=cur.val;
            while(cur!=null && cur.val==value){
                cur=cur.next;
            }
            prev.next=cur;
        }
        else{
            prev=cur;
            cur=cur.next;
        }
            
        }
        
        return head;

    }
}