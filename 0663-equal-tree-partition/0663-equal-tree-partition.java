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
    boolean found = false;
    TreeNode node;
    public boolean checkEqualTree(TreeNode root) {
        int sum = getSum(root);
        if(sum % 2 != 0) {
            return false;
        }
        node = root;
        helper(root, sum/2);
        return found;
    }

    private int getSum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return root.val + getSum(root.left) + getSum(root.right);
    }

    private int helper(TreeNode root, int target) {
        if(root == null) {
            return 0;
        }
        int left = helper(root.left, target), right = helper(root.right, target);
        if(root.val + left + right == target && root != node) {
            found = true;
        }

        return root.val + left + right;
    }
}