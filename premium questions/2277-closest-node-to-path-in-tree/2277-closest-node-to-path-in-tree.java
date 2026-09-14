class Solution {

    private int[] parent;
    private int[] depth;

    public int[] closestNode(int n, int[][] edges, int[][] query) {
        
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        parent = new int[n];
        depth = new int[n];

        dfs(0, -1, 0, graph);

        int[] ans = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            int start = query[i][0];
            int end = query[i][1];
            int node = query[i][2];

            int a = lca(start, end);
            int b = lca(start, node);
            int c = lca(end, node);

            int res = a;

            if (depth[b] > depth[res]) {
                res = b;
            }
            if (depth[c] > depth[res]) {
                res = c;
            }
            ans[i] = res;
        }
        return ans;
    }

    private void dfs(int node, int par, int d, List<Integer>[] graph) {
        parent[node] = par;
        depth[node] = d;

        for (int next : graph[node]) {
            if (next != par) {
                dfs(next, node, d + 1, graph);
            }
        }
    }

    private int lca(int u, int v) {
        while (depth[u] > depth[v]) {
            u = parent[u];
        }
        while (depth[v] > depth[u]) {
            v = parent[v];
        }
        while (u != v) {
            u = parent[u];
            v = parent[v];
        }
        return u;
    }
}