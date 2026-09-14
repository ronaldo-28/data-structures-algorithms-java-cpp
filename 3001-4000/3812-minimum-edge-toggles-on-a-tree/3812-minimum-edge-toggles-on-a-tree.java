class Solution {
    boolean[] visited;
    ArrayList<int[]>[] adjList;
    int[] flips;
    ArrayList<Integer> ans;
    String s;
    String t;

    public List<Integer> minimumFlips(int n, int[][] edges, String start, String target) {
        s = start;
        t = target;
        flips = new int[n];
        
        // Build Adjacency List
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adjList[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            adjList[edge[0]].add(new int[] { edge[1], i });
            adjList[edge[1]].add(new int[] { edge[0], i });
        }
        
        ans = new ArrayList<>();
        visited = new boolean[n];
        
        // Start Post-Order DFS
        dfs(0, -1, -1);
        
        // Sort indices as required
        Collections.sort(ans);

        // If root is still mismatched, it's impossible (no parent to flip)
        if (mismatch(0)) {
            ans = new ArrayList<>();
            ans.add(-1);
        }
        return ans;
    }

    // Helper to check if current state matches target
    // Logic: If flips is even, bit is original. If flips is odd, bit is inverted.
    boolean mismatch(int u){
        return ((flips[u] % 2 == 0) && s.charAt(u) != t.charAt(u))
                    || ((flips[u] % 2 == 1) && s.charAt(u) == t.charAt(u));
    }

    void dfs(int u, int parent, int index) {
        visited[u] = true;
        
        // Process children first
        for (int[] v : adjList[u])
            if (!visited[v[0]])
                dfs(v[0], u, v[1]);
        
        // If current node is mismatched and we have a parent to flip
        if (parent != -1)
            if (mismatch(u)) {
                ans.add(index);
                flips[u]++;
                flips[parent]++;
            }
    }
}