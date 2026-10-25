class Solution {
  public int countKReducibleNumbers(String s, int k) {
    final int MOD = 1_000_000_007;
    int n = s.length();
    int[] steps = new int[n + 2];
    for (int x = 2; x <= n + 1; x++) steps[x] = steps[Integer.bitCount(x)] + 1;
    int[][] c = new int[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      c[i][0] = 1;
      for (int j = 1; j <= i; j++)
        c[i][j] = (c[i - 1][j - 1] + (j <= i - 1 ? c[i - 1][j] : 0)) % MOD;
    }
    long ans = 0;
    int ones = 0;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) != '1') continue;
      int rem = n - i - 1;
      for (int p = Math.max(1, ones); p <= ones + rem; p++) {
        if (steps[p] + 1 <= k) ans += c[rem][p - ones];
      }
      ones++;
    }
    return (int) (ans % MOD);
  }
}