class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        // For each node, keep top 3 neighbors by score
        int[][] top = new int[n][3];
        for (int i = 0; i < n; i++) {
            top[i][0] = -1;
            top[i][1] = -1;
            top[i][2] = -1;
        }
        
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            addNeighbor(top[a], b, scores);
            addNeighbor(top[b], a, scores);
        }
        
        int ans = -1;
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // Try to find node a neighbor of u (not v) and node d neighbor of v (not u, not a)
            for (int i = 0; i < 3; i++) {
                int a = top[u][i];
                if (a == -1 || a == v) continue;
                for (int j = 0; j < 3; j++) {
                    int d = top[v][j];
                    if (d == -1 || d == u || d == a) continue;
                    int total = scores[a] + scores[u] + scores[v] + scores[d];
                    ans = Math.max(ans, total);
                }
            }
        }
        
        return ans;
    }
    
    private void addNeighbor(int[] top, int node, int[] scores) {
        // Insert node into top 3 maintaining sorted order by score descending
        if (top[0] == -1 || scores[node] > scores[top[0]]) {
            top[2] = top[1];
            top[1] = top[0];
            top[0] = node;
        } else if (top[1] == -1 || scores[node] > scores[top[1]]) {
            top[2] = top[1];
            top[1] = node;
        } else if (top[2] == -1 || scores[node] > scores[top[2]]) {
            top[2] = node;
        }
    }
}