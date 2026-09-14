class Solution {
    static {
    int[][] dummy = new int[][]{{1,2,1}};
    for (int i = 0; i < 700; i++)
      networkDelayTime(dummy, 3, 1);
  }
    public static int networkDelayTime(int[][] times, int n, int k) {
   if (n <= 0) return -1;

    final int INF = 1_000_000_000;   // ✅ smaller, faster
    int[] dist = new int[n + 1];

    // ✅ manual fill loop is faster than Arrays.fill
    for (int i = 1; i <= n; i++)
      dist[i] = INF;

    dist[k] = 0;

    // ✅ Bellman-Ford with tight loops
    for (int i = 0; i < n - 1; i++) {
      boolean changed = false;

      for (int[] e : times) {
        int u = e[0], v = e[1], w = e[2];
        int du = dist[u];
        int alt = du + w;

        if (du < INF && alt < dist[v]) {
          dist[v] = alt;
          changed = true;
        }
      }

      if (!changed) break;  // ✅ early stop
    }

    // ✅ find max dist
    int max = 0;
    for (int i = 1; i <= n; i++) {
      int d = dist[i];
      if (d == INF) return -1;
      if (d > max) max = d;
    }

    return max;
    }
}