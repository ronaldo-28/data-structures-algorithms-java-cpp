class Solution {
    final int MODULO =1000000007;

    public int countPaths(int[][] grid) {
        int count = 0;

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int ways=0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                count = (count + dfs(grid, dp, row, col)) % MODULO;
            }
        }
        return count;
    }

    private int dfs(int[][] grid, int[][] dp, int y, int x) {
        if (dp[y][x] != 0)
            return dp[y][x];

        int paths = 1;
        int current = grid[y][x];

        if (y + 1 < grid.length && current < grid[y + 1][x])
            paths = (paths + dfs(grid, dp, y + 1, x)) % MODULO;

        if (y - 1 >= 0 && current < grid[y - 1][x])
            paths = (paths + dfs(grid, dp, y - 1, x)) % MODULO;

        if (x + 1 < grid[y].length && current < grid[y][x + 1])
            paths = (paths + dfs(grid, dp, y, x + 1)) % MODULO;

        if (x - 1 >= 0 && current < grid[y][x - 1])
            paths = (paths + dfs(grid, dp, y, x - 1)) % MODULO;

        dp[y][x] = paths;

        return paths;
    }
}