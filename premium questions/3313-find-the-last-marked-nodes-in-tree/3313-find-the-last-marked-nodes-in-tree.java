class Solution {
    public int[] lastMarkedNodes(int[][] edges) {
        // Build the graph/tree in convenient variables.
        int n = edges.length + 1;
        int[] edgeCounts = new int[n];
        for (int[] edge : edges) {
            edgeCounts[edge[0]]++;
            edgeCounts[edge[1]]++;
        }
        int[][] graph = new int[n][];
        for (int i = n - 1; i >= 0; i--) 
            graph[i] = new int[edgeCounts[i]];
        for (int[] edge : edges) {
            graph[edge[0]][--edgeCounts[edge[0]]] = edge[1];
            graph[edge[1]][--edgeCounts[edge[1]]] = edge[0];
        }

        // Find distances to farthest nodes.
        int[] stack = edgeCounts;   // Recycle the edgecounts[].
        int[] dists1 = new int[n];
        int[] dists2 = new int[n];
        int farNode1 = findFarthestNode(0, new int[n], graph, stack);
        int farNode2 = findFarthestNode(farNode1, dists1, graph, stack);
        findFarthestNode(farNode2, dists2, graph, stack);

        // Build the result in dists1[] instead of allocating 
        // a new array.
        for (int i = n - 1; i >= 0; i--)
            dists1[i] = (dists1[i] >= dists2[i]) ? farNode1 : farNode2;
        return dists1;
    }
    
    // Iterative routine to traverse the tree looking for 
    // the farthest node from the starting node, and filling=in  
    // the passed distances[] array with distances from startNode 
    // to each node in the tree.
    private int findFarthestNode(int startNode, int[] distances, 
                        int[][] graph, int[] stack) {
        int farthestDist = 0;
        int farthestNode = 0;
        distances[startNode] = 1;
        stack[0] = startNode;
        int stackIdx = 0;
        while (stackIdx >= 0) {
            int node = stack[stackIdx--];
            int dist = distances[node];
            if (dist > farthestDist) {
                farthestDist = dist;
                farthestNode = node;
            }
            for (int nextNode : graph[node]) {
                if (distances[nextNode] <= 0) {
                    distances[nextNode] = dist + 1;
                    stack[++stackIdx] = nextNode;
                }
            }
        }
        return farthestNode;
    }
}