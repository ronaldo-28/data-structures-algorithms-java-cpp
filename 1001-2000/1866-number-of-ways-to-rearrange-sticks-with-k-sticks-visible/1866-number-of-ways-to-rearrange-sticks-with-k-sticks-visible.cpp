// Precompute 2D-DP
class Solution {
  constexpr static int M = 1000;  // maximum n
  using Matrix = array<array<int64_t, M + 1>, M + 1>;

  constexpr static int64_t modulo = 1e9 + 7;
  constexpr static int64_t mod(int64_t x) { return x % modulo; }

  const static Matrix init() {
    Matrix dp = {};
    dp[0][0] = 1;
    for (int n = 1; n <= M; ++n) {
      for (int k = 1; k <= min(n, M); ++k) {
        dp[n][k] = mod(dp[n - 1][k - 1] + (n - 1) * dp[n - 1][k]);
      }
    }

    return dp;
  }
  inline const static Matrix dp = init();

 public:
  int rearrangeSticks(int n, int k) { return dp[n][k]; }
};