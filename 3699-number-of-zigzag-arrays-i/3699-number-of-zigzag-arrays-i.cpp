
class Solution {
public:
    static const int mod = 1e9 + 7;

    long long power(long long a, long long b) {
        long long ans = 1;
        while (b) {
            if (b & 1) ans = ans * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return ans;
    }

    int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int t = n / 2;

        vector<long long> fact(2 * m + 1, 1);
        vector<long long> inv(2 * m + 1);

        for (int i = 1; i <= 2 * m; i++)
            fact[i] = fact[i - 1] * i % mod;

        inv[2 * m] = power(fact[2 * m], mod - 2);

        for (int i = 2 * m; i >= 1; i--)
            inv[i - 1] = inv[i] * i % mod;

        auto C = [&](int a, int b) -> long long {
            if (b < 0 || b > a) return 0;
            return fact[a] * inv[b] % mod
                   * inv[a - b] % mod;
        };

        vector<long long> q(t + 1), f(t + 1);
        q[0] = 1;

        for (int i = 1; i <= t && i < m; i++) {
            q[i] = C(m + i - 1, 2 * i);
            if (i & 1) q[i] = (mod - q[i]) % mod;
        }

        for (int i = 0; i <= t; i++) {
            long long v = 0;

            if (n & 1) {
                if (i < m) {
                    v = C(m + i, 2 * i + 1);
                    if (i & 1) v = (mod - v) % mod;
                }
            } else if (i == 0) {
                v = 1;
            }

            for (int j = 1; j <= i && j < m; j++)
                v = (v - q[j] * f[i - j] % mod + mod) % mod;

            f[i] = v;
        }

        return 2 * f[t] % mod;
    }
};