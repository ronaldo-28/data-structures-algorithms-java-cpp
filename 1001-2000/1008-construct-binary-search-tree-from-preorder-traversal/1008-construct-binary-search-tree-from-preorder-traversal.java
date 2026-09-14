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
    int index = 1;

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        helper(root, preorder, 1, preorder.length);
        return root;
    }

    private void helper(TreeNode curr, int[] preorder, int start, int end) {
        if (curr == null) {
            return;
        }

        if (start > end || start >= preorder.length) {
            return;
        }
        TreeNode left = null;
        if (preorder[start] < curr.val) {
            left = new TreeNode(preorder[start]);
            curr.left = left;
        }

        for (int i = start; i < end; i++) {
            if (preorder[i] > curr.val) { // right subtree
                TreeNode right = new TreeNode(preorder[i]);
                curr.right = right;
                helper(curr.left, preorder, start + 1, i);
                helper(curr.right, preorder, i + 1, end);
                return;
            }
        }
        helper(curr.left, preorder, start + 1, end);
    }
}