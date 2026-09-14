class Solution {
    public long maxOutput(int n, int[][] edges, int[] price) {
        //
        int[] deg = new int[n];
        for (int[] e : edges) {
            deg[e[0]]++;
            deg[e[1]]++;
        }

        int[][] g = new int[n][];
        for (int i = 0; i < n; i++) {
            g[i] = new int[deg[i]];
        }

        int[] idx = new int[n];
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u][idx[u]++] = v;
            g[v][idx[v]++] = u;
        }

        int[] parent = new int[n];
        java.util.Arrays.fill(parent, -1);

        int[] order = new int[n];
        int top = 0;
        order[top++] = 0;
        parent[0] = -2;

        for (int i = 0; i < top; i++) {
            int u = order[i];
            for (int v : g[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    order[top++] = v;
                }
            }
        }

        long[] withLeaf = new long[n];
        long[] withoutLeaf = new long[n];
        long ans = 0;

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            withLeaf[u] = price[u];
            withoutLeaf[u] = 0;

            for (int v : g[u]) {
                if (parent[v] != u) continue;

                ans = Math.max(ans,
                        Math.max(
                                withLeaf[u] + withoutLeaf[v],
                                withoutLeaf[u] + withLeaf[v]
                        ));

                withLeaf[u] = Math.max(withLeaf[u],
                        withLeaf[v] + price[u]);

                withoutLeaf[u] = Math.max(withoutLeaf[u],
                        withoutLeaf[v] + price[u]);
            }
        }

        return ans;
    }
}