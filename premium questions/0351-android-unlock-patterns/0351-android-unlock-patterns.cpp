template<int rows, int cols>
consteval array<int, rows * cols + 1> doit() {
    constexpr int N = rows * cols;
    constexpr int M = (1 << N);
    array<int, M * N> DP{0};

    // initialize the DP
    for (int i = 0; i < N; i++) DP[(N << i) + i] = 1; // just do it
    for (int m = 1; m < M; m++) if (__builtin_popcount(m) > 1) {
        for (int last = 0; last < N; last++) if ((m >> last) & 1) {
            const int m2 = m & ~(1 << last);
            for (int pen = 0; pen < N; pen++) if ((m2 >> pen) & 1) {
                // check if can:
                const int r1 = last / cols;
                const int c1 = last % cols;
                const int r2 = pen / cols;
                const int c2 = pen % cols;
                const int g = gcd(r1 - r2, c1 - c2);
                const int dr = (r2 - r1) / g, dc = (c2 - c1) / g;
                bool valid = true;
                for (int k = 1; ; k++) {
                    const int r3 = r1 + k * dr;
                    const int c3 = c1 + k * dc;
                    if (r3 == r2 && c3 == c2) break;
                    const int test = r3 * cols + c3;
                    if (((m >> test) & 1) == 0) {
                        valid = false;
                        break;
                    }
                }
                if (valid) DP[m * N + last] += DP[m2 * N + pen];
            }
        }
    }
    array<int, rows * cols + 1> ans{0};
    for (int m = 0; m < M; m++) for (int last = 0; last < N; last++)
        ans[__builtin_popcount(m)] += DP[m * N + last];
    for (int i = 1; i <= rows * cols; i++) ans[i] += ans[i-1];
    return ans;
}

constexpr auto vals = doit<3, 3>();

class Solution {
public:
    int numberOfPatterns(int m, int n) {
        if (n < m) return 0;
        return vals[n] - vals[m - 1];
    }
};