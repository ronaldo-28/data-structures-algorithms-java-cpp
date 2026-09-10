class Solution {
    public int minLargest(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= m; i++) dp[0][i] = dp[0][i - 1] + 2 - (dp[0][i - 1] & 1 ^ nums2[i - 1]); //base cases

        for(int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + 2 - (dp[i - 1][0] & 1 ^ nums1[i - 1]); //other base cases filled out as we go
            for(int j = 1; j <= m; j++) dp[i][j] = Math.min(dp[i - 1][j] + 2 - (dp[i - 1][j] & 1 ^ nums1[i - 1]), dp[i][j - 1] + 2 - (dp[i][j - 1] & 1 ^ nums2[j - 1]));
        }
        return dp[n][m];
    }
}