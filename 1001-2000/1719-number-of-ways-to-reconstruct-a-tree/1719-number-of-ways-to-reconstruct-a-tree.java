class Solution {
    public int checkWays(int[][] pairs) {
        int N = 501;

        boolean[][] adj = new boolean[N][N];
        List<Integer>[] nei = new ArrayList[N];
        for (int i = 0; i < N; i++) nei[i] = new ArrayList<>();

        // Build graph
        for (int[] p : pairs) {
            int x = p[0], y = p[1];
            adj[x][y] = adj[y][x] = true;
            nei[x].add(y);
            nei[y].add(x);
        }

        // Collect all nodes that appear
        List<Integer> nodes = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (!nei[i].isEmpty()) {
                nodes.add(i);
                adj[i][i] = true; // self reachable for convenience
            }
        }

        // Sort by degree ascending
        nodes.sort(Comparator.comparingInt(a -> nei[a].size()));

        boolean multiple = false;
        int rootCount = 0;

        int m = nodes.size();
        for (int i = 0; i < m; i++) {
            int cur = nodes.get(i);

            // Find parent: first node with higher/equal degree that is connected to cur
            int parentIndex = i + 1;
            while (parentIndex < m && !adj[cur][nodes.get(parentIndex)]) {
                parentIndex++;
            }

            if (parentIndex < m) {
                int parent = nodes.get(parentIndex);

                // If degrees equal, multiple trees possible
                if (nei[cur].size() == nei[parent].size()) {
                    multiple = true;
                }

                // All neighbors of cur must also be neighbors of parent
                for (int nb : nei[cur]) {
                    if (!adj[parent][nb]) {
                        return 0;
                    }
                }
            } else {
                // No parent found => this is a root candidate
                rootCount++;
            }
        }

        if (rootCount != 1) return 0;
        return multiple ? 2 : 1;
    }
}