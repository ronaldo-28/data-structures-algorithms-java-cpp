class Solution {
    public int squareFreeSubsets(int[] nums) {
        final int MOD = 1_000_000_007;
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

        int[] masks = new int[31];
        for (int n = 1; n <= 30; n++) {
            int x = n;
            boolean valid = true;
            int mask = 0;
            for (int i = 0; i < primes.length; i++) {
                int p = primes[i];
                if (x % ((long) p * p) == 0) { valid = false; break; }
                if (x % p == 0) { mask |= (1 << i); x /= p; }
            }
            masks[n] = valid ? mask : -1;
        }

        int[] cnt = new int[31];
        for (int n : nums) cnt[n]++;

        int c1 = cnt[1];

        long[] dp = new long[1 << 10];
        dp[0] = 1;

        for (int n = 2; n <= 30; n++) {
            if (cnt[n] == 0 || masks[n] == -1) continue;
            int m = masks[n];
            int c = cnt[n];
            for (int s = (1 << 10) - 1; s >= 0; s--) {
                if (dp[s] == 0 || (s & m) != 0) continue;
                dp[s | m] = (dp[s | m] + dp[s] * c) % MOD;
            }
        }

        long total = 0;
        for (long v : dp) total = (total + v) % MOD;

        long pow2 = 1;
        for (int i = 0; i < c1; i++) pow2 = pow2 * 2 % MOD;

        return (int) ((total * pow2 % MOD - 1 + MOD) % MOD);
    }
}