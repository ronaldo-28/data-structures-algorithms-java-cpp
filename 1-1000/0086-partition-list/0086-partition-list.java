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
    public ListNode partition(ListNode head, int x) {

        ListNode dummy1=new ListNode(-1);
        ListNode dummy2=new ListNode(-1);
        ListNode d1=dummy1;
        ListNode d2=dummy2;
        
        ListNode temp=head;

        while(temp!=null)
        {
            if(temp.val<x)
            {
                d1.next=temp;
                d1=d1.next;

            }else{

                d2.next=temp;
                d2=d2.next;
            }
            temp=temp.next;
        }
        d1.next=dummy2.next;
        d2.next=null;
        return dummy1.next;
    }
}