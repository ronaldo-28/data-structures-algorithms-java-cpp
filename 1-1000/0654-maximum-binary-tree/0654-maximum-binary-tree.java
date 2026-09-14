class Solution {
    // Declare an array to hold the input values
    private int[] nodeValues;

    /**
     * This method constructs a maximum binary tree from the given array.
     * 
     * @param nums The input array containing elements to be used in the tree.
     * @return The constructed maximum binary tree's root node.
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        this.nodeValues = nums;
        // Start the recursive tree construction process from the full range (0 to length-1)
        return constructTreeInRange(0, nums.length - 1);
    }

    /**
     * This private helper method creates the maximum binary tree recursively.
     * 
     * @param left The left boundary (inclusive) of the current subarray.
     * @param right The right boundary (inclusive) of the current subarray.
     * @return The root node of the constructed subtree.
     */
    private TreeNode constructTreeInRange(int left, int right) {
        // Base case: when the left index is greater than the right, we've gone past the leaf node
        if (left > right) {
            return null;
        }
      
        int maxIndex = left; // Start with the leftmost index
      
        // Find the index of the maximum element in the current subarray
        for (int j = left; j <= right; ++j) {
            if (nodeValues[maxIndex] < nodeValues[j]) {
                maxIndex = j;
            }
        }
      
        // Create a new tree node with the maximum element as its value
        TreeNode root = new TreeNode(nodeValues[maxIndex]);
      
        // Recursively construct the left subtree using elements left to the maximum element
        root.left = constructTreeInRange(left, maxIndex - 1);
      
        // Recursively construct the right subtree using elements right to the maximum element
        root.right = constructTreeInRange(maxIndex + 1, right);
      
        return root; // Return the root node of the constructed subtree
    }
}