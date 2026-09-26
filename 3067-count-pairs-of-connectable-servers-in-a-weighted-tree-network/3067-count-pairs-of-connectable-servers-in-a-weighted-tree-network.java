class Solution {
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = edges.length + 1;
        int[] head = new int[n];
        java.util.Arrays.fill(head, -1);
        
        int edgeCount = edges.length * 2;
        int[] to = new int[edgeCount];
        int[] weight = new int[edgeCount];
        int[] next = new int[edgeCount];
        int idx = 0;
        
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            
            to[idx] = v;
            weight[idx] = w;
            next[idx] = head[u];
            head[u] = idx++;
            
            to[idx] = u;
            weight[idx] = w;
            next[idx] = head[v];
            head[v] = idx++;
        }

        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            int totalValid = 0;
            
            for (int e = head[i]; e != -1; e = next[e]) {
                int neighbor = to[e];
                int branchValid = dfs(neighbor, i, weight[e], signalSpeed, head, to, weight, next);
                
                count[i] += totalValid * branchValid;
                totalValid += branchValid;
            }
        }

        return count;
    }

    private int dfs(int u, int p, int dist, int signalSpeed, int[] head, int[] to, int[] weight, int[] next) {
        int valid = (dist % signalSpeed == 0) ? 1 : 0;
        
        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != p) {
                valid += dfs(v, u, dist + weight[e], signalSpeed, head, to, weight, next);
            }
        }
        
        return valid;
    }
}