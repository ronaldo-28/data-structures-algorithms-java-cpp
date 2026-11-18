class Solution {
	public long minCost(int m, int n, int[][] waitCost) {
		long[] dp = new long[n];
		dp[0] = 1;

		for (int j = 1; j < n; j++) {
			dp[j] = dp[j - 1] + (j + 1L) + waitCost[0][j];
		}

		for (int i = 1; i < m; i++) {
			dp[0] += (i + 1L) + waitCost[i][0];
			for (int j = 1; j < n; j++) {
				dp[j] = Math.min(dp[j], dp[j - 1]) + (long) (i + 1) * (j + 1) + waitCost[i][j];
			}
		}

		return dp[n - 1] - waitCost[m - 1][n - 1];
	}
}