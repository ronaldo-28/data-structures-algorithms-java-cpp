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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        // ListNode dummy = new ListNode(-1);
        // ListNode cur = dummy;

        // ListNode tmp1 = list1;
        // ListNode tmp2 = list2;
        // while(tmp1 != null && tmp2 != null) {
        //     if(tmp1.val <= tmp2.val) {
        //         cur.next = tmp1;
        //         tmp1 = tmp1.next;
        //     } else {
        //         cur.next = tmp2;
        //         tmp2 = tmp2.next;
        //     }
        //     cur = cur.next;
        // }
        // if(tmp1 != null) cur.next = tmp1;
        // if(tmp2 != null) cur.next = tmp2;
        
        // return dummy.next; // newHead

        return merge(list1, list2);
    }



    public ListNode merge(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        
        ListNode cur = null;
        if(list1.val <= list2.val) {
            // cur = list1;
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            // cur = list2;
            list2.next = merge(list1, list2.next);
            return list2;            
        }
    }
}