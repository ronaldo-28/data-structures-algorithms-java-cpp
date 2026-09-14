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

    public int heightOfTree(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return 0;        
        int leftHeight = root.left == null || isLeaf(root.left) ? 0 : heightOfTree(root.left);
        int rightHeight = root.right == null || isLeaf(root.right) ? 0 : heightOfTree(root.right);
        return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
    }

    private boolean isLeaf(TreeNode node) {
        if (node.left == null && node.right == null) return true;
        if (node.left == null ^ node.right == null) return false;
        return (node == node.left.right && node == node.right.left);
    }

}