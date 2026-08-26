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
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return isValidSequence(root, arr, 0);
    }

    public boolean isValidSequence(TreeNode root, int[] arr, int idx) {

        if (root == null || idx == arr.length) return false;

        if (root.val != arr[idx]) return false;

        if (root.left == null && root.right == null && idx == arr.length - 1) return true;
        
        return isValidSequence(root.left, arr, idx + 1) || isValidSequence(root.right, arr, idx + 1);
    }
}