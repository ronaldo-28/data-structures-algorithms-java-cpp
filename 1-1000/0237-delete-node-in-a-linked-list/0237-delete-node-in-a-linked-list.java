class Solution {
    public void deleteNode(ListNode node) {
        // Copy the value of the next node to the current node
        node.val = node.next.val;
        
        // Skip the next node by updating the next pointer
        node.next = node.next.next;
    }
}
