class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null; // No common ancestor
        }

        int rootVal = root.val;
        int pVal = p.val;
        int qVal = q.val;

        if (pVal > rootVal && qVal > rootVal) {
            // Both nodes are in the right subtree
            return lowestCommonAncestor(root.right, p, q);
        } else if (pVal < rootVal && qVal < rootVal) {
            // Both nodes are in the left subtree
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // One node is in the left subtree, and the other is in the right subtree
            // or one of the nodes is equal to the current node (LCA found)
            return root;
        }
    }
}
