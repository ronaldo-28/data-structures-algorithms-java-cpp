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
    private ArrayList<TreeNode> nodes = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        storeBST(root);
        return buildBST(0, nodes.size() - 1);
    }
    private void storeBST(TreeNode root) {
        if (root == null) return;
        storeBST(root.left);
        nodes.add(root);
        storeBST(root.right);
    }
    private TreeNode buildBST(int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode root = nodes.get(mid);
        root.left = buildBST(start, mid - 1);
        root.right = buildBST(mid + 1, end);
        return root;
    }
}