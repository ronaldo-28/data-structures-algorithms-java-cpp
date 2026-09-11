class Solution {
    private int time = 1, current = 0, top = 0;
    public int minRunesToAdd(int n, int[] crystals, int[] flowFrom, int[] flowTo) {
        int m = flowFrom.length;
        int[] id = new int[n]; //stores the id of the strongly connected component for each node
        
        //create adj list
        ArrayList<Integer>[] adj = new ArrayList[n];
        for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for(int i = 0; i < m; i++) adj[flowFrom[i]].add(flowTo[i]);

        //identify strongly connected components
        int[] stack = new int[n], dist = new int[n], min = new int[n];
        boolean[] seen = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(dist[i] == 0) tarjan(i, seen, dist, min, stack, id, adj);
        }
        //we now can treat strongly connected components as singular nodes

        //mark all nodes with a non-zero in-degree
        for(int i = 0; i < m; i++) {
            int val = id[flowTo[i]];
            if(id[flowFrom[i]] != val) seen[val] = true;
        }
        //mark all crystal nodes
        for(int x : crystals) seen[id[x]] = true;

        //count the nodes with a zero in-degree and arent crystal nodes, as these must be connected
        int count = 0;
        for(int i = 0; i < current; i++) {
            if(!seen[i]) count++;
        }
        return count;
    }
    private void tarjan(int index, boolean[] seen, int[] dist, int[] min, int[] stack, int[] id, ArrayList<Integer>[] adj) {
        dist[index] = min[index] = time++;
        seen[index] = true;
        stack[top++] = index;
        for(int next : adj[index]) {
            if(dist[next] == 0) {
                tarjan(next, seen, dist, min, stack, id, adj);
                min[index] = Math.min(min[index], min[next]);
            }else if(seen[next]) min[index] = Math.min(min[index], dist[next]);
        }
        if(min[index] == dist[index]) {
            int root = -1;
            while(root != index) {
                root = stack[--top];
                id[root] = current; //give all nodes in the current component the same id
                seen[root] = false;
            }
            current++;
        }
    }
}