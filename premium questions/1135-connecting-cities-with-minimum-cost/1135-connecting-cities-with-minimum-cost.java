class Solution {
    public int minimumCost(int N, int[][] connections) {
        int n = connections.length;
        int m = connections[0].length;
        int [] parent = new int [N+1];
        if(n < N-1)
            return -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
        for(int i =1;i<=N;i++) {
            parent[i] = i;
        }
        for(int i =0;i<n;i++) {
            pq.add(connections[i]);
        }
        int cost = 0, numberOfEdges = 0;
        while(!pq.isEmpty()) {
            int edge [] =  pq.poll();
            int x = edge[0];
            int y = edge[1];
            int xP = findParent(x, parent);
            int yP = findParent(y, parent);
            if(xP == yP)
                continue;
            cost+=edge[2];
            numberOfEdges++;
           
            if(numberOfEdges == N-1)
                return cost;
             
             parent[xP] = yP;
        }
        return -1;
    }

    private int findParent(int x,  int[] parent) {
        if(parent[x] == x)
            return x;
        
        return parent[x] = findParent(parent[x], parent);
    }

    private boolean isTheGraphConnected(List<Integer> []graph, int N) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(1);
        int visitedNodes = 0;
        int [] visited   = new int [N+1];
        while(!dq.isEmpty()) {
            int x = dq.pollFirst();
            visitedNodes++;
            visited[x] = 1;
            for(int i =0;i<graph[x].size();i++) {
                int nX = graph[x].get(i);
                if(visited[nX] == 0)
                    dq.addLast(nX);
                visited[nX] = 1;
            }
        }
        return visitedNodes == N;
    }
}