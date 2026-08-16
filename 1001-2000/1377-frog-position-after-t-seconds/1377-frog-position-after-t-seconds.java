class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<Integer>[] graph = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n + 1];
        return findProbability(visited, 0, t, target, 1, graph);
    }

    private double findProbability(boolean[] visited, int ct, int t, int target, int cNode, List<Integer>[] graph) {
        visited[cNode] = true;

        if (ct > t)
            return 0.0d;
        if (ct == t && cNode == target)
            return 1.0d;

        int availableNodes = 0;
        double probSum = 0.0d;
        for (int node : graph[cNode]) {
            if (visited[node])
                continue;

            probSum += findProbability(visited, ct + 1, t, target, node, graph);
            availableNodes++;
        }

        if (availableNodes == 0 && cNode == target)
            return 1.0d;
        else if (availableNodes == 0)
            return 0.0d;

        else
            return probSum / availableNodes;
    }

}