class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if the root is null or one of the nodes is found
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search in the left and right subtrees
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);

        // If both nodes are found in different subtrees, the current root is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // If one node is found, return that node as the potential LCA
        return (leftLCA != null) ? leftLCA : rightLCA;
    }
}
