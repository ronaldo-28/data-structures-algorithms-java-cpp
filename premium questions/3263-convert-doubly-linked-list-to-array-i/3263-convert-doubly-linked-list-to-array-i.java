/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
};
*/

class Solution {
    public int[] toArray(Node head) {
        int size = 0;
        Node current = head;

        while (current != null) {
            size++;
            current = current.next;
        }

        int[] result = new int[size];
        current = head;

        for (int i = 0; i < size; i++) {
            result[i] = current.val;
            current = current.next;
        }

        return result;
    }
}