class Solution {
public:
    long long popcountDepth(long long n, int k) {
        if (k == 0) return 1;

        int bits = 0;
        for (long long x = n; x > 0; x >>= 1) {
            ++bits;
        }

        long long choose[51][51] = {};
        for (int i = 0; i <= bits; ++i) {
            choose[i][0] = choose[i][i] = 1;
            for (int j = 1; j < i; ++j) {
                choose[i][j] = choose[i - 1][j - 1] + choose[i - 1][j];
            }
        }

        long long answer = 0;

        for (int ones = 1; ones <= bits; ++ones) {
            int depth = 0;
            for (int x = ones; x > 1; x = __builtin_popcount(x)) {
                ++depth;
            }
            if (depth != k - 1) continue;

            int remaining = ones;
            for (int bit = bits - 1; bit >= 0; --bit) {
                if (((n >> bit) & 1LL) == 0) continue;

                // Choose 0 here, then place the remaining ones freely.
                if (remaining <= bit) {
                    answer += choose[bit][remaining];
                }

                // Choose 1 here to keep matching n's prefix.
                if (--remaining < 0) break;
            }

            if (remaining == 0) ++answer;  // Include n itself.
        }

        // The one-set-bit count includes 1, whose depth is zero.
        return answer - (k == 1);
    }
};