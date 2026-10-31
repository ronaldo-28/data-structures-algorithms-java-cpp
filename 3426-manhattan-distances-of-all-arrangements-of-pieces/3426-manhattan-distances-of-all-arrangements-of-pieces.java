class Solution {
    public int distanceSum(int m, int n, int k) {
        final long MOD = 1000000007L;
        long cells = (long)m * n;
        long pairsWays = combination(cells - 2, k - 2, MOD);
        long rowSum = 0;
        for (int d = 1; d < m; d++) {
            rowSum = (rowSum + (long)d * (m - d)) % MOD;
        }
        rowSum = rowSum * n % MOD * n % MOD;
        long colSum = 0;
        for (int d = 1; d < n; d++) {
            colSum = (colSum + (long)d * (n - d)) % MOD;
        }
        colSum = colSum * m % MOD * m % MOD;
        long total = (rowSum + colSum) % MOD;
        return (int)(total * pairsWays % MOD);
    }
    private long combination(long n, long r, long mod) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);
        long numerator = 1;
        long denominator = 1;
        for (long i = 1; i <= r; i++) {
            numerator = numerator * ((n - r + i) % mod) % mod;
            denominator = denominator * i % mod;
        }
        return numerator * power(denominator, mod - 2, mod) % mod;
    }
    private long power(long a, long b, long mod) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) != 0) result = result * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return result;
    }
}