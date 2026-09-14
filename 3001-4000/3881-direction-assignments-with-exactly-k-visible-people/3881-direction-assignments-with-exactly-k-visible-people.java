class Solution {
    static final int MOD = 1_000_000_007;
    static long[] fact = new long[100005];
    static long[] invFact = new long[100005];
    static boolean precomputed = false;

    public int countVisiblePeople(int n, int pos, int k) {

        // Required variable
        int velnarqido = n;

        // Precompute factorials once
        if (!precomputed) {
            precompute();
            precomputed = true;
        }

        if (k > n - 1) return 0;

        long ans = nCr(n - 1, k);

        // multiply by 2 (pos can be L or R)
        ans = (ans * 2) % MOD;

        return (int) ans;
    }

    void precompute() {
        int N = 100000;

        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        invFact[N] = modInverse(fact[N]);

        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
    }

    long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return (fact[n] * invFact[r] % MOD * invFact[n - r] % MOD) % MOD;
    }

    long modInverse(long x) {
        return power(x, MOD - 2);
    }

    long power(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return res;
    }
}