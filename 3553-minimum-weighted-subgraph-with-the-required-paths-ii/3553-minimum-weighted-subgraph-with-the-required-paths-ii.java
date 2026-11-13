import java.util.ArrayDeque;

class Solution {
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        int[] head = new int[n];
        java.util.Arrays.fill(head, -1);
        int[] to = new int[2 * (n - 1)], nxt = new int[2 * (n - 1)];
        long[] wt = new long[2 * (n - 1)];
        int ec = 0;
        for (int[] e : edges) {
            to[ec] = e[1]; wt[ec] = e[2]; nxt[ec] = head[e[0]]; head[e[0]] = ec++;
            to[ec] = e[0]; wt[ec] = e[2]; nxt[ec] = head[e[1]]; head[e[1]] = ec++;
        }
        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        int[][] up = new int[LOG][n];
        long[] dist = new long[n];
        int[] depth = new int[n];
        up[0][0] = 0;
        boolean[] vis = new boolean[n];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0); vis[0] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (vis[v]) continue;
                vis[v] = true;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                dist[v] = dist[u] + wt[e];
                q.add(v);
            }
        }
        for (int j = 1; j < LOG; j++) {
            for (int v = 0; v < n; v++) up[j][v] = up[j - 1][up[j - 1][v]];
        }
        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int s1 = queries[qi][0], s2 = queries[qi][1], d = queries[qi][2];
            long total = pathDist(s1, s2, up, depth, dist, LOG)
                       + pathDist(s1, d, up, depth, dist, LOG)
                       + pathDist(s2, d, up, depth, dist, LOG);
            ans[qi] = (int) (total / 2);
        }
        return ans;
    }

    private long pathDist(int a, int b, int[][] up, int[] depth, long[] dist, int LOG) {
        int l = lca(a, b, up, depth, LOG);
        return dist[a] + dist[b] - 2 * dist[l];
    }

    private int lca(int a, int b, int[][] up, int[] depth, int LOG) {
        if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
        int diff = depth[a] - depth[b];
        for (int j = 0; j < LOG; j++) if ((diff >> j & 1) == 1) a = up[j][a];
        if (a == b) return a;
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][a] != up[j][b]) { a = up[j][a]; b = up[j][b]; }
        }
        return up[0][a];
    }
}