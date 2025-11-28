class Solution {
    public Node construct(int[][] grid) {
        return constructHelper(grid, 0, 0, grid.length);
    }
    
    // Helper function defined as shown above.
    private Node constructHelper(int[][] grid, int row, int col, int length) {
        // Determine if the current subgrid is uniform.
        boolean isUniform = true;
        int value = grid[row][col]; // The value to compare with.
        for (int i = row; i < row + length; i++) {
            for (int j = col; j < col + length; j++) {
                if (grid[i][j] != value) {
                    isUniform = false;
                    break;
                }
            }
            if (!isUniform) break;
        }
        
        // If the subgrid is uniform, create a leaf node.
        if (isUniform) {
            return new Node(value == 1, true);
        }
        
        // Otherwise, divide the grid into four equal parts and recurse.
        int half = length / 2;
        Node topLeft = constructHelper(grid, row, col, half);
        Node topRight = constructHelper(grid, row, col + half, half);
        Node bottomLeft = constructHelper(grid, row + half, col, half);
        Node bottomRight = constructHelper(grid, row + half, col + half, half);
        
        // Create an internal node with these four children.
        // The value for non-leaf nodes can be arbitrary; here we set it to true.
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
