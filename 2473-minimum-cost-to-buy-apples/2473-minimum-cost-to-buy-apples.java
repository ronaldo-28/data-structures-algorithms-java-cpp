class Solution {
    public long[] minCost(int n, int[][] roads, int[] appleCost, int k) {
        n += 1; // To adjust index since towns are considered from 1 to n-1
        int[] visited = new int[n];
        Arrays.fill(visited, Integer.MAX_VALUE); // Initialize distances with infinity
        visited[0] = 0; // Starting point cost is 0

        // Creating a graph as adjacency list
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Connect 'town 0' to all other towns with appleCost
        for (int i = 1; i < n; i++) {
            graph.get(0).add(new int[] { i, appleCost[i - 1] });
        }

        // Building the graph with given roads and modified costs
        for (int[] road : roads) {
            int src = road[0];
            int dst = road[1];
            int cost = road[2];
            int adjustedCost = (k + 1) * cost; // Adjusting cost as per problem statement
            graph.get(src).add(new int[] { dst, adjustedCost });
            graph.get(dst).add(new int[] { src, adjustedCost });
        }

        // Method to perform BFS using a Queue
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 }); // Start with 'town 0' at cost 0

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentNode = current[0];
            int currentCost = current[1];

            if (visited[currentNode] < currentCost) {
                continue;
            }

            // Explore neighbors
            for (int[] neighbor : graph.get(currentNode)) {
                int nextNode = neighbor[0];
                int travelCost = neighbor[1];
                int newCost = currentCost + travelCost;

                if (visited[nextNode] > newCost) {
                    visited[nextNode] = newCost;
                    queue.add(new int[] { nextNode, newCost });
                }
            }
        }

        // Converting List<Integer> to long[]
        long[] result = new long[n - 1];
        for (int i = 1; i < n; i++) {
            result[i - 1] = visited[i]; // Convert int to long implicitly
        }
        return result;
    }
}