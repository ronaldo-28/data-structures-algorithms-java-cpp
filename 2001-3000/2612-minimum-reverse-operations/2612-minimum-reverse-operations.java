import java.util.Arrays;

class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        // Output array initialized to -1 (representing unreachable blocks)
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        // Track banned or visited positions using a fast boolean array primitive
        boolean[] isBlocked = new boolean[n];
        for (int b : banned) {
            isBlocked[b] = true;
        }
        
        // DSU array tracks the next available unvisited index of the same parity
        // parent[i] points to the next valid unvisited candidate node >= i
        int[] parent = new int[n + 2];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // Boundaries to catch out-of-bounds jumps gracefully
        parent[n] = n;
        parent[n + 1] = n + 1;

        // Mark the initial position as visited and set its baseline operation score to 0
        ans[p] = 0;
        
        // Mark starting node and banned nodes as immediately resolved in DSU
        // Re-pointing to i + 2 preserves the same-parity jumping property
        parent[p] = p + 2;
        for (int i = 0; i < n; i++) {
            if (isBlocked[i]) {
                parent[i] = i + 2;
            }
        }

        // Bare-metal primitive array queue for optimal BFS performance
        int[] queue = new int[n];
        int headPtr = 0;
        int tailPtr = 0;
        
        queue[tailPtr++] = p;

        // Execute optimized BFS traversal loop
        while (headPtr < tailPtr) {
            int u = queue[headPtr++];
            
            // Calculate the absolute minimum and maximum mathematically reachable boundaries
            int minIdx = Math.max(u - k + 1, k - 1 - u);
            int maxIdx = Math.min(u + k - 1, 2 * n - k - 1 - u);
            
            // Start looking from the lowest boundary position
            int current = findNext(minIdx, parent);
            
            // Use DSU pointers to hop directly past all pre-visited elements in O(1)
            while (current <= maxIdx) {
                ans[current] = ans[u] + 1;
                queue[tailPtr++] = current;
                
                // Compress the unvisited state by pointing past this node to current + 2
                parent[current] = current + 2;
                
                // Fetch the next unvisited index belonging to the correct parity block
                current = findNext(current, parent);
            }
        }

        return ans;
    }

    // High performance path-compression routine for DSU lookups
    private int findNext(int i, int[] parent) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = findNext(parent[i], parent);
    }
}