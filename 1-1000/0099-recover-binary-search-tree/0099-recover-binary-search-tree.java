class Solution {
    TreeNode first = null, second = null, prev = null;

    public void recoverTree(TreeNode root) {
        // Morris Traversal
        TreeNode node = root, temp = null;
        while (node != null) {
            if (node.left != null) {
                // Find the inorder predecessor
                temp = node.left;
                while (temp.right != null && temp.right != node) {
                    temp = temp.right;
                }
                // Build the threading; otherwise, restore the tree structure
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                } else {
                    temp.right = null;
                    process(node);
                    node = node.right;
                }
            } else {
                process(node);
                node = node.right;
            }
        }
        // Recover the tree structure
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void process(TreeNode node) {
        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;
            }
            second = node;
        }
        prev = node;
    }
}
