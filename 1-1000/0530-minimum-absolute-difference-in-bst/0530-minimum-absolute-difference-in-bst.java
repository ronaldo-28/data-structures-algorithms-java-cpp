class Solution {
    // Global variables to store the minimum difference and the previous node's value.
    private int minDiff = Integer.MAX_VALUE;
    private Integer prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        // Step 1: Perform in-order traversal of the BST.
        inorder(root);
        // Step 2: Return the computed minimum difference.
        return minDiff;
    }
    
    // Recursive helper method for in-order traversal.
    private void inorder(TreeNode node) {
        if (node == null) return;
        
        // Traverse left subtree.
        inorder(node.left);
        
        // Step 3: Process current node.
        if (prev != null) {
            // Update the minimum difference with the difference between the current node's value and the previous node's value.
            minDiff = Math.min(minDiff, node.val - prev);
        }
        // Update prev to the current node's value.
        prev = node.val;
        
        // Traverse right subtree.
        inorder(node.right);
    }
}
