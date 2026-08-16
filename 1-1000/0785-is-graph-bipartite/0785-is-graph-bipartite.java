class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // 0: uncolored, 1: color1, -1: color2

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) { // If node i is not colored yet, start coloring from it
                if (!dfs(graph, colors, i, 1)) {
                    return false; // If coloring fails for this component, graph is not bipartite
                }
            }
        }
        return true; // If all components are successfully colored, the graph is bipartite
    }

    private boolean dfs(int[][] graph, int[] colors, int node, int color) {
        if (colors[node] != 0) { // If the node is already colored
            return colors[node] == color; // Check if the existing color is the expected color
        }
        colors[node] = color; // Color the current node

        for (int neighbor : graph[node]) {
            if (!dfs(graph, colors, neighbor, -color)) { // Recursively color neighbors with opposite color
                return false; // If coloring neighbor fails, return false
            }
        }
        return true; // Coloring for this component successful
    }
}