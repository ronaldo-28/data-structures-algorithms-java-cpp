class Solution {
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            int max = 0, sum = 0;
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                sum += nums[j];
                cost[i][j] = max * (j - i + 1) - sum;
            }
        }
        int[][] dp = new int[n][k + 2];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) dp[i][1] = cost[0][i];
        for (int j = 2; j <= k + 1; j++) {
            for (int i = 0; i < n; i++) {
                for (int p = 0; p < i; p++) {
                    if (dp[p][j - 1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[p][j - 1] + cost[p + 1][i]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j <= k + 1; j++) res = Math.min(res, dp[n - 1][j]);
        return res;
    }
}