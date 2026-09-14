class Solution {
      // time = O(ElogE) = O(n^2 * logn), space = O(n^2)
    public int minimumCost(int n, int[][] highways, int discounts) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : highways) {
            int a = x[0], b = x[1], c = x[2];
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, 0, 0}); // {cost, node, discount}
        int[][] visited = new int[n][discounts + 1];
        for (int i = 0; i < n; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        visited[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], city = cur[1], dis = cur[2];

            if (city == n - 1) return cost;

            for (int[] x : graph[city]) {
                int next = x[0], weight = x[1];
                if (cost + weight < visited[next][dis]) {
                    pq.offer(new int[]{cost + weight, next, dis});
                    visited[next][dis] = cost + weight;
                }
                if (dis < discounts && cost + weight / 2 < visited[next][dis + 1]) {
                    pq.offer(new int[]{cost + weight / 2, next, dis + 1});
                    visited[next][dis + 1] = cost + weight / 2;
                }
            }
        }
        return -1;
    }
}