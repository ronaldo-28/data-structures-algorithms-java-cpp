class Solution {
    static final long MOD = 1_000_000_007L;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        int[] deg = new int[n + 1];
        for (int[] e : edges) {
            deg[e[0]]++;
            deg[e[1]]++;
        }

        int[][] g = new int[n + 1][];
        for (int i = 1; i <= n; i++) {
            g[i] = new int[deg[i]];
        }

        int[] idx = new int[n + 1];
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u][idx[u]++] = v;
            g[v][idx[v]++] = u;
        }

        int[] depth = new int[n + 1];
        boolean[] vis = new boolean[n + 1];

        int[] q = new int[n];
        int l = 0, r = 0;
        q[r++] = 1;
        vis[1] = true;

        int maxDepth = 0;

        while (l < r) {
            int u = q[l++];
            maxDepth = Math.max(maxDepth, depth[u]);

            for (int v : g[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    depth[v] = depth[u] + 1;
                    q[r++] = v;
                }
            }
        }

        return (int) modPow(2, maxDepth - 1);
    }

    private long modPow(long a, int b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}