class Solution {

    // This is a graph coloring problem.
    public int[] gardenNoAdj(int n, int[][] paths) {
        // degree count
        int[] deg = new int[n];
        for (int[] e : paths) {
            int a = e[0] - 1, b = e[1] - 1;
            deg[a]++; deg[b]++;
        }

        // allocate exact-sized adjacency lists
        int[][] adj = new int[n][];
        for (int i = 0; i < n; i++) adj[i] = new int[deg[i]];

        // fill
        int[] ptr = new int[n];
        for (int[] e : paths) {
            int a = e[0] - 1, b = e[1] - 1;
            adj[a][ptr[a]++] = b;
            adj[b][ptr[b]++] = a;
        }

        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (int nb : adj[i]) {
                int c = color[nb];
                if (c != 0) mask |= 1 << c; // bits 1..4
            }
            for (int c = 1; c <= 4; c++) {
                if ((mask & (1 << c)) == 0) { color[i] = c; break; }
            }
        }
        return color;
    }
}