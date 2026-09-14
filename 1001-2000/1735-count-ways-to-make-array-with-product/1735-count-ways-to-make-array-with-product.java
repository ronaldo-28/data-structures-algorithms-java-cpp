class Solution {
    static final long MOD = 1000000007;
    static final int MAX = 10000;
    static final int[] divs = new int[MAX + 1];
    static final int[] counts = new int[MAX + 1];

    public int[] waysToFillArray(int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = waysToFillArray(queries[i][0], queries[i][1]);
        }
        return res;
    }

    static int waysToFillArray(int n, int k) {
        long[] c = getCounts(n);
        long r = 1;
        int primeIdx = 0;
        while (k > 1) {
            if (divs[k] == 0) primeIdx = find(primeIdx, k);
            r = (r * c[counts[k]]) % MOD;
            k /= divs[k];
        }
        return (int) r;
    }

    static int find(final int primeIdx, final int k) {
        for (int i = primeIdx; i < primes.length; i++) {
            final int p = primes[i];
            if (k % p == 0) {
                int d = p * p;
                int c = 1;
                while (k % d == 0) {
                    c++;
                    d *= p;
                }
                counts[k] = c;
                divs[k] = d / p;
                return primeIdx + 1;
            }
        }
        divs[k] = k;
        counts[k] = 1;
        return primes.length;
    }

    static long[][] cache = new long[MAX + 1][];

    private static long[] getCounts(int n) {
        if (cache[n] != null) return cache[n];
        final long[] c = new long[14];
        c[0] = 1;
        c[1] = n;
        c[2] = n * (n + 1) / 2;
        for (int i = 3; i < c.length; i++) {
            c[i] = (((c[i - 1] * (i + n - 1)) % MOD) * inverses[i - 1]) % MOD;
        }
        return cache[n] = c;
    }

    static long[] inverses = new long[] {1, 500000004, 333333336, 250000002, 400000003, 166666668, 142857144, 125000001, 111111112, 700000005, 818181824, 83333334, 153846155, 71428572};
    static int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
            79, 83, 89, 97};

}