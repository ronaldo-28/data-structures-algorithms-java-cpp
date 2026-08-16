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
        for (int i = 0; i <= 500; i++) {
            reorderList(new ListNode());
        }
    }
    public static void reorderList(ListNode head) {
        ListNode forward=head;
        ListNode reversed=null;
        int x=0;
        while(forward!=null){
            x++;
            reversed=new ListNode(forward.val,reversed);
            forward=forward.next;
            // System.out.println(x);
        }
        ListNode forward2=head;
        while(head!=null&&reversed!=null){
            ListNode temp=head.next;
            head.next=new ListNode(reversed.val,temp );
            reversed=reversed.next;
            head=temp;
        }

        while(forward2!=null){
            x--;
            if(x==0){
                forward2.next=null;
                break;
            }
            forward2=forward2.next;
        }
        
        
    }
}

// 1,2,3,4
// 4,3,2,1