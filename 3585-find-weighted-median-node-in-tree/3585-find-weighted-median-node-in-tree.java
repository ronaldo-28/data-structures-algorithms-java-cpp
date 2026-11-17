class Solution {
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[2 * (n - 1)];
        int[] to = new int[2 * (n - 1)];
        int[] wt = new int[2 * (n - 1)];
        int e = 0;
        for (int[] ed : edges) {
            to[e] = ed[1];
            wt[e] = ed[2];
            nxt[e] = head[ed[0]];
            head[ed[0]] = e++;
            to[e] = ed[0];
            wt[e] = ed[2];
            nxt[e] = head[ed[1]];
            head[ed[1]] = e++;
        }
        int log = 1;
        while ((1 << log) < n) log++;
        int[][] up = new int[log][n];
        int[] depth = new int[n];
        long[] dist = new long[n];
        int[] queue = new int[n];
        boolean[] seen = new boolean[n];
        seen[0] = true;
        int qh = 0;
        int qt = 0;
        queue[qt++] = 0;
        while (qh < qt) {
            int u = queue[qh++];
            for (int x = head[u]; x != -1; x = nxt[x]) {
                int v = to[x];
                if (seen[v]) continue;
                seen[v] = true;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                dist[v] = dist[u] + wt[x];
                queue[qt++] = v;
            }
        }
        for (int k = 1; k < log; k++) {
            for (int v = 0; v < n; v++) up[k][v] = up[k - 1][up[k - 1][v]];
        }
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            int l = lca(u, v, up, depth, log);
            long total = dist[u] + dist[v] - 2 * dist[l];
            if (total == 0) {
                ans[q] = u;
            } else if (2 * (dist[u] - dist[l]) >= total) {
                int x = u;
                for (int k = log - 1; k >= 0; k--) {
                    int a = up[k][x];
                    if (depth[a] >= depth[l] && 2 * (dist[u] - dist[a]) < total) x = a;
                }
                ans[q] = up[0][x];
            } else {
                int x = v;
                for (int k = log - 1; k >= 0; k--) {
                    int a = up[k][x];
                    if (depth[a] > depth[l] && 2 * (dist[u] + dist[a] - 2 * dist[l]) >= total) x = a;
                }
                ans[q] = x;
            }
        }
        return ans;
    }

    int lca(int a, int b, int[][] up, int[] depth, int log) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < log; k++) {
            if (((diff >> k) & 1) == 1) a = up[k][a];
        }
        if (a == b) return a;
        for (int k = log - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }
        return up[0][a];
    }
}