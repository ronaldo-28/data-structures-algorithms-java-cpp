class Solution {
    public int[] distanceToCycle(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[n];
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int next : graph[node]) {

                indegree[next]--;
                if (indegree[next] == 1) {
                    q.offer(next);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (indegree[i] >= 2) {
                q.add(i);
                res[i] = 0;
            }
        }
        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                size--;
                int node = q.poll();
                res[node] = distance;
                for (int next : graph[node]) {
                    if (res[next] != -1) {
                        continue;
                    }
                    q.offer(next);
                }
            }
            distance++;
        }
        return res;
    }
}