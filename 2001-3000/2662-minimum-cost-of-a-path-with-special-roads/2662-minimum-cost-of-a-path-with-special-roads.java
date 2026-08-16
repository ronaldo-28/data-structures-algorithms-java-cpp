class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int n = specialRoads.length;
        
        // dist[i] represents the minimum cost to reach the END of specialRoads[i]
        // dist[n] represents the minimum cost to reach the final target
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        
        // Step 1: Initialize distances by walking directly from 'start'
        for (int i = 0; i < n; i++) {
            // Distance = (Walk to road start) + (Take the special road cost)
            dist[i] = Math.abs(start[0] - specialRoads[i][0]) + 
                      Math.abs(start[1] - specialRoads[i][1]) + 
                      specialRoads[i][4];
        }
        // Direct walking cost from start to target
        dist[n] = Math.abs(start[0] - target[0]) + Math.abs(start[1] - target[1]);
        
        // Step 2: Array-based Dijkstra loop running exactly N + 1 times
        for (int step = 0; step <= n; step++) {
            int u = -1;
            int minDist = Integer.MAX_VALUE;
            
            // Linear scan to find the minimum unvisited destination
            for (int i = 0; i <= n; i++) {
                if (!visited[i] && dist[i] < minDist) {
                    minDist = dist[i];
                    u = i;
                }
            }
            
            // If we can't reach anything else, or the closest node is the target, we are done
            if (u == -1 || u == n) {
                break;
            }
            
            visited[u] = true;
            
            // Current coordinates are the END point of the special road 'u'
            int currX = specialRoads[u][2];
            int currY = specialRoads[u][3];
            
            // 1. Try relaxing the path directly to the final target
            int toTarget = dist[u] + Math.abs(currX - target[0]) + Math.abs(currY - target[1]);
            if (toTarget < dist[n]) {
                dist[n] = toTarget;
            }
            
            // 2. Try relaxing paths to the endpoints of all other special roads
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    // Cost = (Walk from u's end to v's start) + (Take road v)
                    int weight = Math.abs(currX - specialRoads[v][0]) + 
                                 Math.abs(currY - specialRoads[v][1]) + 
                                 specialRoads[v][4];
                    
                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }
        
        return dist[n];
    }
}