  class Solution {
    int[][] g;
    int[] top, queue;

    public int magnificentSets(int n, int[][] edges) {
      g = new int[n + 1][];
      top = new int[n + 1];
      queue = new int[n + 1];
      int[] degree = new int[n + 1];

      for (int[] edge : edges) {
        degree[edge[0]]++;
        degree[edge[1]]++;
        top[edge[0]]++;
        top[edge[1]]++;
      }
      Arrays.setAll(g, i -> new int[top[i]]);

      for (int[] edge : edges) {
        g[edge[0]][--top[edge[0]]] = edge[1];
        g[edge[1]][--top[edge[1]]] = edge[0];
      }
      int[] comp = new int[n + 1];
      int component = 1;
      int[][] res = new int[n + 1][2];
      for (int i = 1; i <= n; i++) {
        if (comp[i] != 0 && res[comp[i]][1] < degree[i])
          continue;
        if (comp[i] == 0)
          comp[i] = component++;
        res[comp[i]][1] = degree[i];
        int[] groups = new int[n + 1];

        int left = 0;
        int right = -1;

        groups[i] = 1;

        queue[++right] = i;
        int m = 0;
        while (left <= right) {
          int size = right - left + 1;
          for (int j = 0; j < size; j++) {
            int node = queue[left++];
            comp[node] = comp[i];
            m = Math.max(m, groups[node]);
            for (int nd : g[node]) {
              if (groups[nd] == 0) {
                groups[nd] = groups[node] + 1;

                queue[++right] = nd;
              } else if (Math.abs(groups[nd] - groups[node]) != 1) {
                return -1;
              }
            }
          }
        }
        res[comp[i]][0] = Math.max(res[comp[i]][0], m);
      }
      int ans = 0;
      for (int[] i : res) {
        ans += i[0];
      }
      return ans;
    }
  }