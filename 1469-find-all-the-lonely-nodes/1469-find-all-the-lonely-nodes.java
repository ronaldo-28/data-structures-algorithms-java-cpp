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
    public List<Integer> getLonelyNodes(TreeNode root) {
        var nodes = new ArrayList<Integer>();
        addLonelyNodes(nodes, root, false);
        return nodes;
    }
    
    private static void addLonelyNodes(List<Integer> nodes, TreeNode root, boolean lonely) {
        if (root == null) return;
        if (lonely) nodes.add(root.val);

        addLonelyNodes(nodes, root.left, root.right == null);
        addLonelyNodes(nodes, root.right, root.left == null);
    }
}