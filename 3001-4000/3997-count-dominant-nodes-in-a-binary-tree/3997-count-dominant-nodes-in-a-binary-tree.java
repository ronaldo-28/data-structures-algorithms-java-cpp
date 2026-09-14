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
    int ans = 0; 
    private int cnt(TreeNode root) {
        if(root == null) return Integer.MIN_VALUE; 
        int left = cnt(root.left); 
        int right = cnt(root.right); 
        if(root.val >= Math.max(left, right)) {
            ans++; 
        }
        return Math.max(root.val, Math.max(left, right)); 
    }
    public int countDominantNodes(TreeNode root) {
        ans = 0; 
        cnt(root); 
        return ans; 
    }
}