class Solution {
    // This function returns the intersection of two quadtrees.
    public Node intersect(Node quadTree1, Node quadTree2) {
        // Call the recursive DFS function to traverse the trees.
        return dfs(quadTree1, quadTree2);
    }

    // Private helper function that performs DFS on the quadtrees to find the intersection.
    private Node dfs(Node tree1, Node tree2) {
        // If both nodes are leaves, return a new leaf node that carries the intersection of their values.
        if (tree1.isLeaf && tree2.isLeaf) {
            return new Node(tree1.val || tree2.val, true, null, null, null, null);
        }
        // If the first node is a leaf and its value is true, or the second node is a leaf and its value is true.
        // Then return the node with the true value, as it takes precedence in the intersection.
        if (tree1.isLeaf) {
            return tree1.val ? tree1 : tree2;
        }
        if (tree2.isLeaf) {
            return tree2.val ? tree2 : tree1;
        }
        // Create a new parent node for the result.
        Node result = new Node();
        // Recursively compute the intersection for each corresponding child.
        result.topLeft = dfs(tree1.topLeft, tree2.topLeft);
        result.topRight = dfs(tree1.topRight, tree2.topRight);
        result.bottomLeft = dfs(tree1.bottomLeft, tree2.bottomLeft);
        result.bottomRight = dfs(tree1.bottomRight, tree2.bottomRight);

        // Check if all children are leaves and have the same value.
        boolean allChildrenAreLeaves = result.topLeft.isLeaf && result.topRight.isLeaf && result.bottomLeft.isLeaf && result.bottomRight.isLeaf;
        boolean allChildrenHaveSameValue = result.topLeft.val == result.topRight.val
                && result.topRight.val == result.bottomLeft.val && result.bottomLeft.val == result.bottomRight.val;

        // If all children are leaves and have the same value, the parent becomes a leaf node.
        if (allChildrenAreLeaves && allChildrenHaveSameValue) {
            result = new Node(result.topLeft.val, true, null, null, null, null);
        }
        return result; // Return the computed intersection node.
    }
}