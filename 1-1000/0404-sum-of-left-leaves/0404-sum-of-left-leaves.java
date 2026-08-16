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
    public int sumOfLeftLeaves(TreeNode root) {
        // Base case: if the root is null, return 0
        if (root == null) {
            return 0;
        }

        int sum = 0;

        // Check if the left child exists
        if (root.left != null) {
            // Check if the left child is a leaf node
            if (root.left.left == null && root.left.right == null) {
                // Add the value of the left leaf node to the sum
                sum += root.left.val;
            } else {
                // Recursively call the function for the left subtree
                sum += sumOfLeftLeaves(root.left);
            }
        }

        // Recursively call the function for the right subtree
        sum += sumOfLeftLeaves(root.right);

        return sum;
    }
}
