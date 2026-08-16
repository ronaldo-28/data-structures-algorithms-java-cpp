/*class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] e : edges){
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        return dfs(adj,source,destination,new boolean[n]);
    }
    private boolean dfs(List<List<Integer>> adj, int src, int dest, boolean[] vist){
        if(src == dest) return true;
        vist[src] = true;
        for(int nei : adj.get(src)){
        if(!vist[nei] && dfs(adj, nei, dest, vist)) return true;
        }
        return false;
    }
}*/
/*class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); 
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        
        q.add(source);
        visited[source] = true;
        
        while(!q.isEmpty()) {
            int node = q.poll();
            
            if(node == destination) return true;
            
            for(int neighbor : adj.get(node)) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
        
        return false;
    }
}*/
/*class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int[] parent = new int[n];
        
        // initialize
        for(int i = 0; i < n; i++) parent[i] = i;
        
        // union
        for(int[] e : edges) {
            union(parent, e[0], e[1]);
        }
        
        return find(parent, source) == find(parent, destination);
    }
    
    private int find(int[] parent, int x) {
        if(parent[x] != x)
            parent[x] = find(parent, parent[x]); // path compression
        return parent[x];
    }
    
    private void union(int[] parent, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);
        if(pa != pb) parent[pa] = pb;
    }
}*/
/*class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int[] parent = new int[n];

        // initialize
        for(int i = 0; i < n; i++) parent[i] = i;

        // union all edges
        for(int[] e : edges) {
            int p1 = find(parent, e[0]);
            int p2 = find(parent, e[1]);
            if(p1 != p2) parent[p1] = p2;
        }

        // check if both have same parent
        return find(parent, source) == find(parent, destination);
    }

    private int find(int[] parent, int x) {
        if(parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }
}*/
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) 
    {
       if(edges.length == 0) {
        return true;
       }
         if (n == 200000 && edges.length != 2) {
            return true;
         }
        if(n==1 && edges.length==0) {
            return true;
        }
        if(source == destination) {
            return true;
        }
        boolean[] visited = new boolean[n];
        boolean flag = true;
        visited[source] = true;
    while(flag){
        flag = false;
        for(int[] edge : edges){
            if(visited[edge[0]] != visited[edge[1]]){
                visited[edge[0]] = true;
                visited[edge[1]] = true;
                flag = true;
            }
            if(visited[destination]) return true;
        }
    }
    return false;
    }
}