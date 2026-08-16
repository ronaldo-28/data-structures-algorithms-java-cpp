class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        int[] extraEdge = {0, 0};
        for (int[] edge : edges) {
            if (parent[edge[1]] > 0) {
                extraEdge = edge;
            } else {
                parent[edge[1]] = edge[0];
            }
        }

        if (extraEdge[0] == 0) {
            boolean[] seen = new boolean[edges.length + 1];
            for (int[] edge : edges) {
                if (seen[edge[0]] && seen[edge[1]]) {
                    return edge;
                }
                seen[edge[0]] = true; 
                seen[edge[1]] = true;
            }
        } 
        
        for (int current = extraEdge[1]; current != 0; current = parent[current]) {
            if (parent[current] == extraEdge[1]) {
                return new int[] {parent[extraEdge[1]], extraEdge[1]};
            }
        }

        return extraEdge;
    }
}