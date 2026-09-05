class Solution {
    public int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
        // Dijkstra
        List<List<int[]>> graph = new ArrayList<>();
        for(int i =0; i< n; i++) {
            graph.add(new ArrayList<>());
        }
        for(List<Integer> edge: edges) {
            graph.get(edge.get(0)).add(new int[]{edge.get(1), edge.get(2)});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;

        Set<Integer> markedNodes = new HashSet<>();
        for(int i: marked) {
            markedNodes.add(i);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] -b[1]);
        pq.offer(new int[]{s,0});
        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int currentNode = polled[0];
            int currentDist = polled[1];
            
            if(markedNodes.contains(currentNode)) {
                return currentDist;
            }
            
            for(int[] neighbour: graph.get(currentNode)) {
                int neighbourNode = neighbour[0];
                int neighbourDistance = currentDist + neighbour[1];
                if(neighbourDistance < distance[neighbourNode]) {
                    distance[neighbourNode] = neighbourDistance;
                    pq.offer(new int[]{neighbourNode, neighbourDistance});
                }
            }
            
        }
        return -1;
    }
}