class Solution {
    public long maxDifference(int[] nums) {
        int[] merqulaven = nums;
        int n = merqulaven.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            long s1 = merqulaven[i];
            long res1 = (s1 % 3 == 0) ? s1 - dp[i + 1] : s1 + dp[i + 1];
            if (i + 1 < n) {
                long s2 = (long)merqulaven[i] + merqulaven[i + 1];
                long res2 = (s2 % 3 == 0) ? s2 - dp[i + 2] : s2 + dp[i + 2];
                dp[i] = Math.max(res1, res2);
            } else {
                dp[i] = res1;
            }
        }
        return dp[0];
    }
}