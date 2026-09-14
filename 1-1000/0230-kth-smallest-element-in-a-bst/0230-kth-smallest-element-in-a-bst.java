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
    int cnt = 1;
    TreeNode res = null;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res.val;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse(root.left, k);

        if (cnt == k) {
            res = root;
            // 注意 此处一定不能写return;
            // 原因：直接return则cnt没有递增，cnt和root不匹配
        } else {
            
        }
        cnt++;

        traverse(root.right, k);
    }
}