  class Solution {

    int[] bobTime;
    int max = Integer.MIN_VALUE;
    int[][] g;
    int[] top;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
      int n = edges.length + 1;

      g = new int[n][];
      top = new int[n];

      for (int[] e : edges) {
        top[e[0]]++;
        top[e[1]]++;
      }
      java.util.Arrays.setAll(g, i -> new int[top[i]]);
      for (int[] e : edges) {
        int a = e[0];
        int b = e[1];
        g[a][--top[a]] = b;
        g[b][--top[b]] = a;
      }

      bobTime = new int[n];
      int[] dest = g[bob];
      for (int i = 0; i < dest.length; i++) {
        int node = dest[i];
        if (dfsBob(bob, node, 1)) {
          bobTime[bob] = 0;
          break;
        }
      }

      for (int i = 0; i < n; i++)

        if (i != bob && bobTime[i] == 0)
          bobTime[i] = Integer.MAX_VALUE;

      for (int i : g[0])
        dfsAlice(0, i, amount[0], 1, amount);

      return max;
    }

    private void dfsAlice(int src, int curr, int currNet, int time, int[] amount) {
      int[] list = g[curr];

      if (bobTime[curr] == time)
        currNet += amount[curr] / 2;
      else if (bobTime[curr] > time)
        currNet += amount[curr];

      if (curr != 0 && list.length == 1) {
        max = Math.max(max, currNet);
        return;
      }

      for (int i : list)
        if (i != src)
          dfsAlice(curr, i, currNet, time + 1, amount);

    }

    private boolean dfsBob(int src, int curr, int time) {
      if (curr == 0) {
        bobTime[curr] = time;
        return true;
      }

      int[] dest = g[curr];

      for (int i : dest)
        if (i != src)
          if (dfsBob(curr, i, time + 1)) {
            bobTime[curr] = time;
            return true;
          }

      return false;

    }
  }