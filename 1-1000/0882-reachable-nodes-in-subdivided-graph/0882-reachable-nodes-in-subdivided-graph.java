class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        int[][][] adj = buildGraph(edges, n);
        int[] dist = new int[n];
        Arrays.fill(dist, maxMoves + 1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {0, 0});
        dist[0] = 0;
        
        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int index = current[0], time = current[1];
            if(time > dist[index]) continue;
            for(int[] next : adj[index]) {
                int nextIndex = next[0], nextTime = time + next[1] + 1;
                if(nextTime < dist[nextIndex]) {
                    dist[nextIndex] = nextTime;
                    pq.offer(new int[] {nextIndex, nextTime});
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
            if(dist[i] == maxMoves + 1) dist[i] = maxMoves;
            else ans++; 
        }
        for(int[] edge : edges) ans += Math.min(edge[2], 2 * maxMoves - dist[edge[0]] - dist[edge[1]]);
        return ans;
    }
    private static int[][][] buildGraph(int[][] edges, int n) {
        int[] degree = new int[n];
        for(int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        int[][][] adj = new int[n][][];
        for(int i = 0; i < n; i++) adj[i] = new int[degree[i]][];
        for(int[] edge : edges) {
            int a = edge[0], b = edge[1], c = edge[2];
            adj[a][--degree[a]] = new int[] {b, c};
            adj[b][--degree[b]] = new int[] {a, c};
        }
        return adj;
    }
}