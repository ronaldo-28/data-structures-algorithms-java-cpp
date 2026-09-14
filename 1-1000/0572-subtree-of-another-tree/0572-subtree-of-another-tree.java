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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return(isSub(root, subRoot, subRoot));
    }

    private boolean isSub(TreeNode root, TreeNode subRoot, TreeNode originalSubRoot) {
        if(subRoot == null)  {
            return root == null;
        } 
        else if(root == null) {
            return false;
        }
        else {
            boolean result = false;
            if(root.val == subRoot.val) {
                result = isSub(root.left, subRoot.left, originalSubRoot) && 
                isSub(root.right, subRoot.right, originalSubRoot);
            }
            if(!result && root.val == originalSubRoot.val) {
                result = isSub(root.left, originalSubRoot.left, originalSubRoot) &&
                isSub(root.right, originalSubRoot.right, originalSubRoot);
            }
            if(!result) {
                result = isSub(root.left, originalSubRoot, originalSubRoot) ||
                isSub(root.right, originalSubRoot, originalSubRoot);
            }
            return result;
        }
    }
}