class Solution {
    int[] parent, rank, xr;

    private int find(int x) {
        if (parent[x] == x) return x;
        int p = parent[x];
        parent[x] = find(parent[x]);
        xr[x] ^= xr[p]; // update XOR distance to root
        return parent[x];
    }

    public int numberOfEdgesAdded(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        xr = new int[n];

        for (int i = 0; i < n; i++) parent[i] = i;

        int ans = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];

            int ru = find(u);
            int rv = find(v);

            int xu = xr[u];
            int xv = xr[v];

            if (ru == rv) {
                // same component: check if adding edge keeps all cycles even
                if ((xu ^ xv) == w) ans++;
            } else {
                // merge two components
                if (rank[ru] < rank[rv]) {
                    int temp = ru; ru = rv; rv = temp;
                    temp = xu; xu = xv; xv = temp;
                }

                parent[rv] = ru;
                xr[rv] = xu ^ xv ^ w;

                if (rank[ru] == rank[rv]) rank[ru]++;
                ans++;
            }
        }

        return ans;
    }
}