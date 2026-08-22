/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        // Create a new node with the value to be inserted
        Node insertNode = new Node(insertVal);

        // Edge case: If the list is empty, create a single-node circular list
        if (head == null) {
            insertNode.next = insertNode;
            return insertNode;
        }

        // Start from the given head node
        Node p = head;

        // Traverse the list to find the insertion point
        while (p.next != head) {
            // Case 1: Normal ascending order in the list (p.val <= p.next.val)
            if (p.val <= p.next.val) {
                // If insertVal is between p.val and p.next.val, break
                if (p.val <= insertVal && insertVal <= p.next.val) {
                    break;
                }
            } else {
                // Case 2: At the boundary between max and min values (p.val > p.next.val)
                // Insert if insertVal is either the new max or the new min
                if (insertVal >= p.val || insertVal <= p.next.val) {
                    break;
                }
            }

            // Move to the next node
            p = p.next;
        }

        // Insert the new node between p and p.next
        insertNode.next = p.next;
        p.next = insertNode;

        // Return the original head of the list
        return head;
    }
}