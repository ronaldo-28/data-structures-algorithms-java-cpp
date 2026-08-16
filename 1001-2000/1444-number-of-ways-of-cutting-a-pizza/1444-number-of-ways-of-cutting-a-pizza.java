class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] apple;
    private Integer[][][] dp;
    private int n, m;

    public int ways(String[] pizza, int k) {
        n = pizza.length;
        m = pizza[0].length();
        apple = new int[n + 1][m + 1];
        dp = new Integer[n + 1][m + 1][k + 1];

        // Precompute apple presence using prefix sum
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                apple[i][j] = (pizza[i].charAt(j) == 'A' ? 1 : 0)
                            + apple[i + 1][j] + apple[i][j + 1] - apple[i + 1][j + 1];
            }
        }

        return solve(0, 0, k - 1);
    }

    private int solve(int i, int j, int cutsLeft) {
        if (apple[i][j] == 0) return 0; // No apples left, invalid cut
        if (cutsLeft == 0) return 1;    // If no more cuts are needed, this is a valid way

        if (dp[i][j][cutsLeft] != null) return dp[i][j][cutsLeft];

        int ans = 0;

        // Vertical cuts
        for (int v = j + 1; v < m; v++) {
            if (apple[i][j] - apple[i][v] > 0) { // Ensure left partition has at least one apple
                ans = (ans + solve(i, v, cutsLeft - 1)) % MOD;
            }
        }

        // Horizontal cuts
        for (int h = i + 1; h < n; h++) {
            if (apple[i][j] - apple[h][j] > 0) { // Ensure top partition has at least one apple
                ans = (ans + solve(h, j, cutsLeft - 1)) % MOD;
            }
        }

        return dp[i][j][cutsLeft] = ans;
    }
}