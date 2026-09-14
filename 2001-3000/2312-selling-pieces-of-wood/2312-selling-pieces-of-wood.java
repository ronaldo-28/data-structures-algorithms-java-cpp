class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        // price[h][w] = direct selling price for exactly h x w, or 0 if unavailable.
        long[][] price = new long[m + 1][n + 1];
        for (int[] p : prices) {
            int h = p[0];
            int w = p[1];
            price[h][w] = p[2];
        }

        // dp[h][w] = maximum revenue obtainable from one h x w rectangle.
        long[][] dp = new long[m + 1][n + 1];

        for (int h = 1; h <= m; h++) {
            for (int w = 1; w <= n; w++) {
                long best = price[h][w]; // Option 1: sell whole piece directly.

                // Option 2: first cut is horizontal.
                // Only iterate to h / 2 because splits are symmetric:
                // cut at x is same partition as cut at h - x.
                for (int cut = 1; cut <= h / 2; cut++) {
                    best = Math.max(best, dp[cut][w] + dp[h - cut][w]);
                }

                // Option 3: first cut is vertical.
                for (int cut = 1; cut <= w / 2; cut++) {
                    best = Math.max(best, dp[h][cut] + dp[h][w - cut]);
                }

                dp[h][w] = best;
            }
        }

        return dp[m][n];
    }
}