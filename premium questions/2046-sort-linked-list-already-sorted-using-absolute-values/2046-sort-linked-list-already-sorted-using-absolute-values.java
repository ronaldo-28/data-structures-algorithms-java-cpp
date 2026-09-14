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
    public ListNode sortLinkedList(ListNode head) {
	
		// check if linkedlist has only 1 node. If so just return the head no need of sorting
        if(head.next == null)
            return head;
        
		
		// take a curr node that will be used for looping through linkedlist
		// take prev and next nodes with initial node as head
		// prev and next node will be used for breaking negative node link n adding positive node link
        ListNode curr = head, prev = head, next = head;
        
		
		// loop through the linkedlist until we reach the end of the linkedlist
        while(curr != null){
		
			// check if the actual value is +ve. If yes, then
            if(curr.val >= 0){
			
				//store current node as prev
                prev = curr;
				
				//move to next node
                curr = curr.next;
            }
			//If no i.e., actual value is -ve
            else{
				
				// store the next node
                next = curr.next;
				
				// point current node's (-ve value node) next to head
                curr.next = head;
				
				// make the current node as new head
                head = curr;
				
				// point prev node's next to next node. this is to break the link from -ve node
                prev.next = next;
				
				// move to next node
                curr = next;
            }
        }
        
		
		//return new head
        return head;
    }
}