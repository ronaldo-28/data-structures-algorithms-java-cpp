class Solution {
  public int[] baseUnitConversions(int[][] conversions) {
    final long MOD = 1_000_000_007L;
    int n = conversions.length + 1;
    int[] head = new int[n], next = new int[n - 1];
    java.util.Arrays.fill(head, -1);
    for (int i = 0; i < n - 1; i++) {
      next[i] = head[conversions[i][0]];
      head[conversions[i][0]] = i;
    }
    long[] val = new long[n];
    val[0] = 1;
    int[] queue = new int[n];
    int qh = 0, qt = 0;
    queue[qt++] = 0;
    while (qh < qt) {
      int u = queue[qh++];
      for (int e = head[u]; e >= 0; e = next[e]) {
        int v = conversions[e][1];
        val[v] = val[u] * conversions[e][2] % MOD;
        queue[qt++] = v;
      }
    }
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) ans[i] = (int) val[i];
    return ans;
  }
}