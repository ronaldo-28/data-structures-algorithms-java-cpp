class Solution {
    public int goodSubtreeSum(int[] vals, int[] par) {
        int n = vals.length;
        int MOD = 1000000007;
        
        // Build the adjacency list for the tree
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[par[i]].add(i);
        }
        
        // Precompute the digit mask for each node
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            int v = vals[i];
            int m = 0;
            boolean possible = true;
            while (v > 0) {
                int d = v % 10;
                if (((m >> d) & 1) == 1) {
                    possible = false;
                    break;
                }
                m |= (1 << d);
                v /= 10;
            }
            masks[i] = possible ? m : -1;
        }
        
        // Post-order traversal layout via standard BFS/DFS queue order
        List<Integer> order = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order.add(u);
            for (int v : adj[u]) {
                stack.push(v);
            }
        }
        
        long[][] dp = new long[n][1024];
        long totalSum = 0;
        
        // Process nodes from leaves up to the root
        for (int i = order.size() - 1; i >= 0; i--) {
            int u = order.get(i);
            long[] curDp = new long[1024];
            
            for (int v : adj[u]) {
                long[] nextDp = curDp.clone();
                long[] childDp = dp[v];
                
                for (int m2 = 1; m2 < 1024; m2++) {
                    if (childDp[m2] == 0) continue;
                    int comp = 1023 ^ m2;
                    int sub = comp;
                    while (true) {
                        if (curDp[sub] > 0 || sub == 0) {
                            long val = curDp[sub] + childDp[m2];
                            if (val > nextDp[sub | m2]) {
                                nextDp[sub | m2] = val;
                            }
                        }
                        if (sub == 0) break;
                        sub = (sub - 1) & comp;
                    }
                }
                curDp = nextDp;
            }
            
            // Optionally include the current node u itself
            int uM = masks[u];
            if (uM != -1) {
                long[] nextDp = curDp.clone();
                if (vals[u] > nextDp[uM]) {
                    nextDp[uM] = vals[u];
                }
                int comp = 1023 ^ uM;
                int sub = comp;
                while (true) {
                    if (curDp[sub] > 0 || sub == 0) {
                        long val = curDp[sub] + vals[u];
                        if (val > nextDp[sub | uM]) {
                            nextDp[sub | uM] = val;
                        }
                    }
                    if (sub == 0) break;
                    sub = (sub - 1) & comp;
                }
                curDp = nextDp;
            }
            
            dp[u] = curDp;
            
            long maxVal = 0;
            for (long val : curDp) {
                if (val > maxVal) maxVal = val;
            }
            totalSum = (totalSum + maxVal) % MOD;
        }
        
        return (int) totalSum;
    }
}