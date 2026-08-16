class Solution {

    static final int MOD = 1000000007;

    public int countValidSequences(int n, int k) {
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        invFact[n] = modPow(fact[n], MOD - 2);

        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }

        long total = nCr(n - 1, k - 1, fact, invFact);

        long odd = 0;
        if (((n - k) & 1) == 0) {
            int m = (n - k) / 2;
            odd = nCr(m + k - 1, k - 1, fact, invFact);
        }

        return (int) ((total - odd + MOD) % MOD);
    }

    private long nCr(int n, int r, long[] fact, long[] invFact) {
        if (r < 0 || r > n)
            return 0;
        return (((fact[n] * invFact[r]) % MOD) * invFact[n - r]) % MOD;
    }

    private long modPow(long a, long b) {
        long res = 1;

        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a) % MOD;
            }

            a = (a * a) % MOD;
            b /= 2;
        }

        return res;
    }
}