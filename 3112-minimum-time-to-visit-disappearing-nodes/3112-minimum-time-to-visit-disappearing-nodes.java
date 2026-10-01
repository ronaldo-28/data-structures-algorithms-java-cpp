import java.util.Arrays;

class Solution {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int m = edges.length;
        
        // Forward Star graph representation
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] to = new int[2 * m];
        int[] weight = new int[2 * m];
        int[] next = new int[2 * m];
        int edgeCount = 0;
        
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            
            to[edgeCount] = v;
            weight[edgeCount] = w;
            next[edgeCount] = head[u];
            head[u] = edgeCount++;
            
            to[edgeCount] = u;
            weight[edgeCount] = w;
            next[edgeCount] = head[v];
            head[v] = edgeCount++;
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        // Primitive Min-Heap: (time << 32) | node
        long[] heap = new long[edges.length * 2 + 10];
        int size = 0;
        heap[++size] = 0L; // (0 << 32) | 0
        
        while (size > 0) {
            long top = heap[1];
            heap[1] = heap[size--];
            
            // Sift Down
            int p = 1;
            while (p * 2 <= size) {
                int child = p * 2;
                if (child + 1 <= size && heap[child + 1] < heap[child]) child++;
                if (heap[p] <= heap[child]) break;
                
                long tmp = heap[p];
                heap[p] = heap[child];
                heap[child] = tmp;
                p = child;
            }
            
            int d = (int) (top >>> 32);
            int u = (int) (top & 0xFFFFFFFFL);
            
            if (d > dist[u]) continue; // Stale state bypass
            
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int w = weight[e];
                int nextTime = d + w;
                
                // Only proceed if it's a strictly shorter path AND the node hasn't disappeared
                if (nextTime < dist[v] && nextTime < disappear[v]) {
                    dist[v] = nextTime;
                    
                    long nextState = ((long) nextTime << 32) | v;
                    heap[++size] = nextState;
                    
                    // Sift Up
                    int curr = size;
                    while (curr > 1 && heap[curr] < heap[curr / 2]) {
                        long tmp = heap[curr];
                        heap[curr] = heap[curr / 2];
                        heap[curr / 2] = tmp;
                        curr /= 2;
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        
        return dist;
    }
}