class Solution {
    public int minimumPossibleSum(int n, int target) {
        final long MOD = 1_000_000_007L;

        // Choose 1..m. Using (target - 1) / 2 avoids selecting
        // two values whose sum equals target.
        long m = Math.min(n, (target) / 2);

        long firstPart = m * (m + 1) / 2;

        // Remaining values: target, target + 1, ..., target + k - 1
        long k = n - m;
        long secondPart = k * target + k * (k - 1) / 2;

        return (int) ((firstPart + secondPart) % MOD);
    }
}