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

    int result ;
    public int longestUnivaluePath(TreeNode root) {
        result = 0;
        rec(root);
        return result > 0 ? result - 1 : 0;
    }

    private int rec(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int left = rec(root.left);
        int right = rec(root.right);

        int len = 1;
        int recResult = 1;
        if (root.left != null && root.left.val == root.val) {
            len += left;
            recResult += left;
        }
        if (root.right != null && root.right.val == root.val) {
            len += right;
            recResult = Math.max(recResult, 1 + right);
        }

        result = Math.max(result, len);
        return recResult;
    }

}