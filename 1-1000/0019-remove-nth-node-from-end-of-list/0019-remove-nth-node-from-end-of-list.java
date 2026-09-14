class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            return head.next;
        }
        ListNode temp2 = head;
        ListNode prev = null;
        while (temp != null) {
            temp = temp.next;
            prev = temp2;
            temp2 = temp2.next;
        }
        prev.next = prev.next.next;
        return head;
    }
}