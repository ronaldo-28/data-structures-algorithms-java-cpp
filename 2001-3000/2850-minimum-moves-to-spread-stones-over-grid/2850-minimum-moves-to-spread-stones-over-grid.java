class Solution {
    public int minimumMoves(int[][] grid) {
        return dfs(grid);
    }

    private int dfs(int[][] grid) {
        int min = Integer.MAX_VALUE;

        // Find first empty cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (grid[i][j] == 0) {

                    // Try every cell having extra stones
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {

                            if (grid[x][y] > 1) {

                                grid[x][y]--;
                                grid[i][j]++;

                                int cost = Math.abs(i - x) + Math.abs(j - y);

                                min = Math.min(min, cost + dfs(grid));

                                // Backtrack
                                grid[x][y]++;
                                grid[i][j]--;
                            }
                        }
                    }

                    return min;
                }
            }
        }

        // No empty cells left
        return 0;
    }
}