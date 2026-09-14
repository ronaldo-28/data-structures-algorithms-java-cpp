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
    public int rob(TreeNode root) {
        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }

    // Helper function that returns int array:
    // result[0] = max money obtained including current node
    // result[1] = max money obtained excluding current node
    private int[] robHelper(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0}; // {rob, not rob}
        }

        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);

        int robCurrent = node.val + left[1] + right[1]; // rob current -> can't rob children
        int notRobCurrent = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // don't rob current -> can rob children

        return new int[]{robCurrent, notRobCurrent};
    }
}