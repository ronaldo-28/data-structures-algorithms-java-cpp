class Solution {

    private static int MOD = 1_000_000_007;

    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int[][][] dp = new int[n][m][k];

        // Initialise dp[0][0][k].
        dp[0][0][grid[0][0] % k] = 1;

        // Initialise first column dp[i][0][k].
        int vj = grid[0][0] % k;
        for (int i = 1; i < n; i++) {
            vj += grid[i][0];
            vj %= k;
            dp[i][0][vj] = 1;
        }

        // Initialise first row dp[0][j][k].
        int vi = grid[0][0] % k;
        for (int j = 1; j < m; j++) {
            vi += grid[0][j];
            vi %= k;
            dp[0][j][vi] = 1;
        }

        // Solve dp.
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                for (int x = 0, p = (x + grid[i][j]) % k; x < k; x++, p++) {
                    if (p >= k) {
                        p -= k;
                    }
                    dp[i][j][p] = (dp[i - 1][j][x] + dp[i][j - 1][x]) % MOD;
                }
            }
        }
        return dp[n - 1][m - 1][0];
    }
}