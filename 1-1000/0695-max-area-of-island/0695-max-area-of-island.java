public class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // Iterate through every cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the cell is land, perform DFS to find the island's area
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j);
                    // Update maxArea if the found area is larger
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int row, int col) {
        // Check for out-of-bounds or if the cell is water
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }

        // Mark the cell as visited by setting it to 0
        grid[row][col] = 0;

        // Initialize area for the current cell
        int area = 1;

        // Explore all four directions
        area += dfs(grid, row + 1, col); // Down
        area += dfs(grid, row - 1, col); // Up
        area += dfs(grid, row, col + 1); // Right
        area += dfs(grid, row, col - 1); // Left

        return area;
    }
}
