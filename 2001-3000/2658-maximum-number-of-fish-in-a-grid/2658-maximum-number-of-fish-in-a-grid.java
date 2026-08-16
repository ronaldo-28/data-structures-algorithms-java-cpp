class Solution {
    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxFish = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    maxFish = Math.max(maxFish, dfs(grid, i, j));
                }
            }
        }

        return maxFish;
    }

    private int dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        // Check for out of bounds or land cell or already visited water cell
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }

        int fish = grid[r][c];
        grid[r][c] = 0; // Mark as visited (sink the cell)

        // Explore adjacent cells
        fish += dfs(grid, r + 1, c); // Down
        fish += dfs(grid, r - 1, c); // Up
        fish += dfs(grid, r, c + 1); // Right
        fish += dfs(grid, r, c - 1); // Left

        return fish;
    }
}