class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int mx = -10000, mn = 10000;
        for (int a : arr) {
            if (a > mx) {
                mx = a;
            }
            if (a < mn) {
                mn = a;
            }
        }
        int[] dp = new int[mx-mn+1];
        int res = 1;
        for (int a : arr) {
            int cur = a - mn, p = a - difference;
            if (p < mn || p > mx) {
                dp[cur] = 1;
            } else {
                dp[cur] = 1 + dp[p-mn];
                res = Math.max(dp[cur], res);
            }
        }
        return res;
    }
}