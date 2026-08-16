class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n == 1) {
            return 0;
        }
        int minCost = 0;
        int curr = 0;
        int next = 0;
        boolean[] visited = new boolean[n];
        int processed = 0;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        while (processed != n) {
            curr = next;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    int d = points[curr][0] - points[i][0];
                    int val = Math.max(d, -d);
                    d = points[curr][1] - points[i][1];
                    val += Math.max(d, -d);
                    dist[i] = Math.min(dist[i], val);
                    if (dist[i] < min) {
                        min = dist[i];
                        next = i;
                    }
                }
            }
            processed++;
            visited[next] = true;
            minCost += min;
        }

        return minCost;
    }
}