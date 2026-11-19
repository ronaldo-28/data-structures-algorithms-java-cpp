class Solution {
    int[] size;
    int[] parent;
    public int minCost(int n, int[][] edges, int k) {

        if (k >= n)
            return 0;

        int low = 0;
        int high = 0;

        for (int[] edge : edges) {
            high = Math.max(high, edge[2]);
        }

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (canMake(n, edges, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    boolean canMake(int n, int[][] edges, int k, int maxWeight) {

        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int components = n;

        for (int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            if (weight > maxWeight)
                continue;

            int pu = findParent(u);
            int pv = findParent(v);

            if (pu != pv) {
                union(pu, pv);
                components--;
            }
        }

        return components <= k;
    }

    public int findParent(int u){
        if(parent[u] == u){
            return u;
        }
        return parent[u] = findParent(parent[u]);
    }

    void union(int a, int b){
         if (size[a] < size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        parent[b] = a;
        size[a] += size[b];
    }
}