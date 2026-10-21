class Solution {
  public int maxRemovals(String source, String pattern, int[] targetIndices) {
    int n = source.length(), m = pattern.length();
    boolean[] target = new boolean[n];
    for (int t : targetIndices) target[t] = true;
    int NEG = Integer.MIN_VALUE / 2;
    int[] dp = new int[m + 1];
    java.util.Arrays.fill(dp, NEG);
    dp[0] = 0;
    for (int i = 0; i < n; i++) {
      int t = target[i] ? 1 : 0;
      char c = source.charAt(i);
      for (int j = Math.min(i + 1, m); j >= 1; j--) {
        int v = dp[j] + t;
        if (c == pattern.charAt(j - 1) && dp[j - 1] > v) v = dp[j - 1];
        dp[j] = v;
      }
      dp[0] += t;
    }
    return dp[m];
  }
}