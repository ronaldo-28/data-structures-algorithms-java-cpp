import java.util.Arrays;

class Solution {
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        if (n <= 1) return 0;

        // Step 1: Track degrees of each node
        int[] degree = new int[n];
        
        // Forward Star (Compressed Adjacency List) arrays to eliminate object overhead
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] next = new int[2 * n];
        int[] to = new int[2 * n];
        int edgeIdx = 0;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            degree[u]++;
            degree[v]++;
            
            // Inline edge addition
            to[edgeIdx] = v;
            next[edgeIdx] = head[u];
            head[u] = edgeIdx++;
            
            to[edgeIdx] = u;
            next[edgeIdx] = head[v];
            head[v] = edgeIdx++;
        }

        // Fast primitive array queue for topological sorting
        int[] queue = new int[n];
        int headPtr = 0;
        int tailPtr = 0;

        // Track deleted nodes using a lightweight boolean array
        boolean[] deleted = new boolean[n];

        // Step 2: Push all leaf nodes that do NOT have a coin into the queue
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && coins[i] == 0) {
                queue[tailPtr++] = i;
                deleted[i] = true;
            }
        }

        // Process coinless leaf pruning iteratively
        while (headPtr < tailPtr) {
            int u = queue[headPtr++];
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (!deleted[v]) {
                    degree[v]--;
                    if (degree[v] == 1 && coins[v] == 0) {
                        queue[tailPtr++] = v;
                        deleted[v] = true;
                    }
                }
            }
        }

        // Step 3: Now find all leaf nodes that DO have coins left
        headPtr = 0;
        tailPtr = 0;
        for (int i = 0; i < n; i++) {
            if (!deleted[i] && degree[i] == 1) {
                queue[tailPtr++] = i;
            }
        }

        // Track layers pruned (we need to prune exactly 2 layers of coin leaves)
        int[] layer = new int[n];
        
        while (headPtr < tailPtr) {
            int u = queue[headPtr++];
            
            // If we have already reached the 2nd layer, stop pruning further up the branches
            if (layer[u] == 2) continue;
            
            deleted[u] = true;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (!deleted[v]) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        layer[v] = layer[u] + 1;
                        queue[tailPtr++] = v;
                    }
                }
            }
        }

        // Step 4: Count the remaining valid edges
        int remainingEdges = 0;
        for (int[] edge : edges) {
            if (!deleted[edge[0]] && !deleted[edge[1]]) {
                remainingEdges++;
            }
        }

        return remainingEdges * 2;
    }
}