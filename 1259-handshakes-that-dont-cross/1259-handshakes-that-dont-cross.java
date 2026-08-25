class Solution {
    final int MOD = 1_000_000_007;
    static int[] memo = new int[1001];
    static {
        Arrays.fill(memo, -1);
        memo[0] = 1;
        memo[1] = 0;
        memo[2] = 1;
    }

    public int numberOfWays(int n) {
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = 0;
        for (int i = 1; i < n; i++) {
            memo[n] = (int) (((long) memo[n] + (long) numberOfWays(i - 1) * (long) numberOfWays(n - (i - 1) - 2)) % MOD);
        }
        return memo[n];
    }
}