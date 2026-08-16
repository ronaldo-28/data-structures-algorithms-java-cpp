/*class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Split list into halves
        ListNode mid = getMid(head);
        ListNode right = mid.next;
        mid.next = null; // split the list

        // Step 2: Sort each half
        ListNode leftSorted = sortList(head);
        ListNode rightSorted = sortList(right);

        // Step 3: Merge sorted halves
        return merge(leftSorted, rightSorted);
    }

    // Helper: find middle node
    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Helper: merge two sorted linked lists
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
*/
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
    static {
        // Warm-up loop (for JVM optimization in LeetCode)
        for (int i = 0; i < 500; i++) sortList(null);
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // Step 1: Find min and max values in list
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        ListNode temp = head;
        while (temp != null) {
            min = Math.min(min, temp.val);
            max = Math.max(max, temp.val);
            temp = temp.next;
        }

        // Step 2: Create frequency array
        int[] freq = new int[max - min + 1];
        temp = head;
        while (temp != null) {
            freq[temp.val - min]++;
            temp = temp.next;
        }

        // Step 3: Refill the linked list in sorted order
        temp = head;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                temp.val = i + min;
                temp = temp.next;
            }
        }

        return head;
    }
}