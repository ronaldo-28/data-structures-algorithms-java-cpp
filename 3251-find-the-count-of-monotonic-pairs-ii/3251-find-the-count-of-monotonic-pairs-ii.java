class Solution {
    private static final int MOD = 1_000_000_007;

    public int countOfPairs(int[] nums) {
        int n = nums.length;
        long sumD = 0;

        // Calculate forced minimum increments
        for (int i = 1; i < n; i++) {
            sumD += Math.max(0, nums[i] - nums[i - 1]);
        }

        long M = nums[n - 1] - sumD;
        if (M < 0) return 0; // Impossible to satisfy non-increasing arr2

        // Calculate (M + n) C n % MOD = ((M + n)! / (M! * n!)) % MOD
        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= n; i++) {
            numerator = (numerator * (M + i)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }

    private long modInverse(long a, int mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}