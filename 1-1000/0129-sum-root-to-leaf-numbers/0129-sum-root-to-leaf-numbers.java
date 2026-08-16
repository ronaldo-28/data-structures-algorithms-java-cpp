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
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] res = new int[]{0};
        sumNumbers(root, 0, res);
        return res[0];
    }

    private void sumNumbers(TreeNode root, int sum, int[] res) {
        sum *= 10;
        sum += root.val;
        
        if (root.left == null && root.right == null) {
            res[0] += sum;
        }

        if (root.left != null) {
            sumNumbers(root.left, sum, res);
        }
        if (root.right != null) {
            sumNumbers(root.right, sum, res);
        }
    }
}