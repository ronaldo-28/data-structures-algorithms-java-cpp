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
    public int dfs(TreeNode root){
        if(root==null) return 0;
        int l=dfs(root.left);
        int r=dfs(root.right);
        if((l&(l+1))==0 && l/2<=r && r<=l)
            return l+r+1;
        if((r&(r+1))==0 && r<=l && l<=2*r+1)
            return l+r+1;

        return -1;
    }
    public boolean isCompleteTree(TreeNode root) {
        return dfs(root)>=0;
    }
}