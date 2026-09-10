/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
};
*/

class Solution {
    public int[] toArray(Node curr) {
        
        while(curr.prev!=null)
        {
            curr=curr.prev;
        }
        int count=0;
        Node head=curr;
        while(curr!=null)
        {
           count++; 
           curr=curr.next;
        }
        int []ans =new int[count];
        count=0;
        while(head!=null)
        {
            ans[count++]=head.val;
            head=head.next;
        }
        return ans;



    }
}