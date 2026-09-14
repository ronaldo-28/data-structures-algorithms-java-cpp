import java.util.ArrayList;
import java.util.List;

class Solution {
    // Define explicit constants for our 3-state tracking mechanism
    private static final int UNVISITED = 0;
    private static final int VISITING = 1; // Currently in the active recursion path (Gray)
    private static final int VALIDATED = 2; // Fully verified to safely lead to destination (Black)

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        // Step 1: Build the directional graph adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        // Step 2: Immediate check - If the destination has outgoing edges, it's an invalid trap
        if (!graph[destination].isEmpty()) {
            return false;
        }

        // Step 3: Initialize our state array tracking cache
        int[] state = new int[n];

        // Step 4: Run our recursive DFS check starting from the source
        return dfsCheck(source, destination, graph, state);
    }

    private boolean dfsCheck(int node, int dest, List<Integer>[] graph, int[] state) {
        // 1. If we hit a node currently in our active path, we found a cycle loop!
        if (state[node] == VISITING) {
            return false;
        }
        
        // 2. If this node has already been fully validated before, skip it safely
        if (state[node] == VALIDATED) {
            return true;
        }

        // 3. Base Case: If this is a dead-end node, it MUST be our destination target
        if (graph[node].isEmpty()) {
            return node == dest;
        }

        // 4. Set state to VISITING before checking children branches
        state[node] = VISITING;

        // 5. Recursively inspect all outgoing neighbor paths
        for (int neighbor : graph[node]) {
            if (!dfsCheck(neighbor, dest, graph, state)) {
                return false; // If even ONE sub-path fails or loops, the entire query fails
            }
        }

        // 6. If all paths passing through this node are clean, mark it as VALIDATED
        state[node] = VALIDATED;
        return true;
    }
}