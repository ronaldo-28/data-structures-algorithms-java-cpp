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
    int count;
    public int equalToDescendants(TreeNode root) {
        count=0;
        solve(root);
        return count;
    }
    public int solve(TreeNode root){
        if(root==null){
            return 0;
        }
        int left=solve(root.left);
        int right=solve(root.right);
        if(root.val==left+right){
            count++;
        }
        return root.val+left+right;
    }
}