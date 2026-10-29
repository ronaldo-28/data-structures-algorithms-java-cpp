class Solution {
    public int countPathsWithXorValue(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int MOD = 1_000_000_007;

        int[][] dp = new int[n][16];

        dp[0][grid[0][0]] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) continue;

                int[] cur = new int[16];
                int val = grid[i][j];

                for (int x = 0; x < 16; x++) {
                    int prev = x ^ val;

                    if (i > 0)
                        cur[x] = dp[j][prev];

                    if (j > 0)
                        cur[x] = (cur[x] + dp[j - 1][prev]) % MOD;
                }

                dp[j] = cur;
            }
        }

        return dp[n - 1][k];
    }
}