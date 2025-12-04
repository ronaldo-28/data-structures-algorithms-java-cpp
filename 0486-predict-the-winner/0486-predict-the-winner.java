class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n]; // both inclusive
        for (int i = 0; i < n; i++)
            dp[i][i] = nums[i];
        for (int len = 1; len < n; len++) {
            for (int l = 0; l + len < n; l++) {
                int r = l + len;
                dp[l][r] = Math.max(nums[l] - dp[l+1][r], nums[r] - dp[l][r-1]);
            }
        }
        return dp[0][n-1] >= 0;
    }
}