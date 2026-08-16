class Solution {
    public static int countHousePlacements(int n) {
        long MOD = 1_000_000_007L;

        // 1. Get the total combinations for ONE side using your logic
        long waysForOneSide = calculate(n, MOD);

        // 2. Square it for BOTH sides (since the sides are completely independent)
        long totalWays = (waysForOneSide * waysForOneSide) % MOD;

        return (int) totalWays;
    }

    private static int calculate(int n, long mod) {
        // We pass the mod through to keep the numbers small during recursion
        return helper(1, 1, 0, n, mod);
    }

    private static int helper(long curr, long prev, int i, int n, long mod) {
        if (i == n) {
            return (int) curr;
        }

        // Apply the modulo at each addition step to prevent integer overflow
        long nextCurr = (curr + prev) % mod;

        return helper(nextCurr, curr, i + 1, n, mod);
    }
}