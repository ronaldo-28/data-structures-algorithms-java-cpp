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
    public String gameResult(ListNode head) {
        ListNode currentNode = head;
        int pointDiffernce = 0;

        while (currentNode != null) {  
            if (currentNode.next != null) {
                pointDiffernce += (currentNode.val > currentNode.next.val ? 1 : -1);
            }
            currentNode = currentNode.next.next;
        }

        if (pointDiffernce < 0) {
            return "Odd";
        } else if (pointDiffernce > 0) {
            return "Even";
        } else {
            return "Tie";
        }
    }
}