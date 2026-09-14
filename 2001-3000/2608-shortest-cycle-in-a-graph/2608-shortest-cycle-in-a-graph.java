
  class Solution {
    int[] v;
    int[] dist;
    int[][] g;
    int ans = (int) 1e9;
    int inf = 1001;

    public int findShortestCycle(int n, int[][] edges) {
      g = new int[n][];
      int[] top = new int[n];
      v = new int[n];
      dist = new int[n];

      for (var e : edges) {
        top[e[0]]++;
        top[e[1]]++;
      }

      Arrays.setAll(g, i -> new int[top[i]]);

      for (var e : edges) {
        g[e[0]][--top[e[0]]] = e[1];
        g[e[1]][--top[e[1]]] = e[0];
      }
      Arrays.fill(dist, inf);
      for (int j = 0; j < n; j++)

        if (dist[j] == inf)
          dfs(j, -1, 0);

      return ans == (int) 1e9 ? -1 : ans;

    }

    void dfs(int u, int p, int time) {

      dist[u] = time;

      for (int nbr : g[u]) {
        if (nbr == p)
          continue;
        if (dist[nbr] > dist[u] + 1)
          dfs(nbr, u, dist[u] + 1);

        else if (dist[u] > dist[nbr])
          ans = Math.min(ans, dist[u] - dist[nbr] + 1);
      }

    }
  }