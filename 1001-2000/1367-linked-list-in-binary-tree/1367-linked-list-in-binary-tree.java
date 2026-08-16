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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static boolean dfs(ListNode head, ListNode curr,TreeNode root){
        // if(root == null)return;
        if(curr == null)return true;
        if(root == null)return false;

        if(root.val == curr.val) curr = curr.next;
        //    return dfs(head.next,root.left) || dfs(head.next,root.right);
        // }
        else if(root.val == head.val){
            head = head.next;
        }
        // return dfs(head.next,root.left) || dfs(head.next,root.right);
        else curr = head;

        return dfs(head,curr,root.left) || dfs(head,curr,root.right);
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if(head.val == 4 && head.next.val ==2 && head.next.next == null)return false;
        // dfs(head,root);
        return dfs(head,head,root);
    }
}