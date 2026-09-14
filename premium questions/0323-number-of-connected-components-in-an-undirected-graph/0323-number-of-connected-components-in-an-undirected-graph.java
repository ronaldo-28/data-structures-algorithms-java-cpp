class Solution {
    public int countComponents(int n, int[][] edges) {

        int[] parent = new int[n];
        int[] rank = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int components = n;
        for(int[] edge: edges) {
            components -= union(edge[0], edge[1], parent, rank);
        }

        return components;
        
    }

    int union(int a, int b, int[] parent, int[] rank) {
        int rootA = find(a, parent);
        int rootB = find(b, parent);

        if (rootA == rootB)
            return 0;
        
        if(rank[rootA] < rank[rootB]) {
            int temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        rank[rootA] += rank[rootB];
        return 1;
    }

    int find(int p, int[] parent) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}