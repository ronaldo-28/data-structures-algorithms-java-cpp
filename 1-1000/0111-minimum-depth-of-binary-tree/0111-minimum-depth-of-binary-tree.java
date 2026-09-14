class Solution {
    private int min = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        solve(root, 0);
        return min;
    }

    private void solve(TreeNode root, int depth) {
        depth++;
        if (depth == min) {
            return;
        }

        if (isLeaf(root)) {
            min = Math.min(min, depth);
            return;
        }

        if (root.left != null && (root.right == null || root.right.right != null || root.right.left != null))
            solve(root.left, depth);
        if (root.right != null)
            solve(root.right, depth);

    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}