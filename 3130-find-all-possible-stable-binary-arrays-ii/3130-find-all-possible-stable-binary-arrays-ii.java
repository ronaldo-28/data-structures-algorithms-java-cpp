class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int N = 1000;

    private long[] fact;
    private long[] invFact;

    public int numberOfStableArrays(int zero, int one, int limit) {
        init();

        long ans = 0;
        long[] cache = new long[one + 1];

        int mx = Math.min(zero, one) + 1;

        for (int g0 = (zero + limit - 1) / limit; g0 <= Math.min(zero, mx); g0++) {
            long ways0 = calc(g0, zero, limit);

            for (int g1 = Math.max(g0 - 1, (one + limit - 1) / limit);
                 g1 <= Math.min(g0 + 1, one);
                 g1++) {

                long ways1;
                if (cache[g1] != 0) {
                    ways1 = cache[g1];
                } else {
                    ways1 = calc(g1, one, limit);
                    cache[g1] = ways1;
                }

                ans = (ans + ways0 * ways1 % MOD * (g0 == g1 ? 2 : 1)) % MOD;
            }
        }

        return (int)((ans % MOD + MOD) % MOD);
    }

    private void init() {
        if (fact != null) return;

        fact = new long[N + 1];
        invFact = new long[N + 1];

        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[N] = modPow(fact[N], MOD - 2);

        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }

    private long calc(int groups, int total, int limit) {
        long res = 0;
        int sign = 1;

        for (int k = 0; k <= groups && k * limit <= total - groups; k++) {
            long cur = comb(groups, k) * comb(total - k * limit - 1, groups - 1) % MOD;

            if (sign == 1)
                res = (res + cur) % MOD;
            else
                res = (res - cur + MOD) % MOD;

            sign = -sign;
        }

        return res;
    }

    private long comb(int n, int r) {
        if (r < 0 || r > n || n < 0) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    private long modPow(long a, long e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return res;
    }
}