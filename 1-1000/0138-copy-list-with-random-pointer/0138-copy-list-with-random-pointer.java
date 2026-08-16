/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        insertInBetween(head);
        mapRandomPointers(head);

       return mapNextPointers(head);
    }

    public void insertInBetween(Node head){
        Node temp = head;

        while(temp != null){
            Node copyNode = new Node(temp.val);
            copyNode.next = temp.next;
            temp.next = copyNode;
          
            temp = temp.next.next;
        }
    }

    public void mapRandomPointers(Node head){
          Node temp = head;

        while(temp != null){
            Node copyNode = temp.next;
            if(temp.random != null){
                copyNode.random = temp.random.next;
            }else{
                copyNode.random = null;
            }

            temp = temp.next.next;
        }
    }

    public Node mapNextPointers(Node head){
        Node temp = head;
        Node dummy = new Node(-1);
        Node res = dummy;

        while(temp != null){
            res.next = temp.next;
            res = res.next;

            temp.next = temp.next.next;
            temp = temp.next;
        }

        return dummy.next;
    }


}