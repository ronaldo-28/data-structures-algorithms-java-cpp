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
    int total = 0 ;
    int low;
    int high;
    public int rangeSumBST(TreeNode root, int low, int high) {
        this.low = low;
        this.high = high;
        dfs(root);
        return total;
    }

    void dfs(TreeNode root){
        if(root == null)
            return;
        
        if(root.val >= low && root.val <= high)
            total += root.val;
        
        if(low <= root.val)
            dfs(root.left);
        
        if(high >= root.val)
            dfs(root.right);
    }
}