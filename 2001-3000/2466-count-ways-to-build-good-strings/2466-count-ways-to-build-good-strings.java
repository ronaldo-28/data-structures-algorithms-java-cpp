class Solution {
    private static final int MOD = 1_000_000_007;
    static {
        for(int i = 0; i < 100; i++) {
            countGoodStrings(3, 4, 1, 2);
        }
    }
    public static int countGoodStrings(int low, int high, int zero, int one) {
        long[] dp = new long[Math.max(zero, one) + high + 1];
        dp[zero]++; dp[one]++;
        long sum = 0;
        for(int i = 0; i <= high; i++) {
            long num = dp[i] % MOD;
            dp[i + zero] += num;
            dp[i + one] += num;
            if(i >= low) sum += num;
        }
        return (int) (sum % MOD);
    }
}