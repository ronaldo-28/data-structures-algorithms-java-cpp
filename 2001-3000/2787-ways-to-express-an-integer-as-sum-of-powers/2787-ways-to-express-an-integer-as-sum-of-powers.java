class Solution {
    public int numberOfWays(int n, int x) {
        long[] dp = new long[n+1];
        dp[0] = 1;
        for (int i = 1; Math.pow(i,x) <= n; i++) {
            int num = (int)Math.pow(i,x);
            for (int s = n; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }
        return (int) (dp[n] % 1_000_000_007);
        
    }
}