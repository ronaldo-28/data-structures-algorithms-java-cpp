/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    ListNode pt1, pt2;
    ListNode helper() {
        while (pt1 != null && pt2 != null) {
            if (pt1 == pt2) return pt1;
            pt1 = pt1.next;
            pt2 = pt2.next;
        }
        return null;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        pt1 = headA;
        pt2 = headB;
        ListNode res1 = helper();

        if (pt1 == null) pt1 = headB;
        if (pt2 == null) pt2 = headA;
        ListNode res2 = helper();

        if (pt1 == null) pt1 = headB;
        if (pt2 == null) pt2 = headA;
        ListNode res3 = helper();

        return res1 != null ? res1 : (res2 != null ? res2 : res3);
    }
}