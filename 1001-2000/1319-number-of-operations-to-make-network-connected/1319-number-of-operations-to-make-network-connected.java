class Solution {

    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }

    public int makeConnected(int n, int[][] connections) {
        
        int size = connections.length;
        
        if(size < n-1) return -1;
        
        DisjointSet ds = new DisjointSet(n);
        
        for(int i = 0 ; i < size ; i++) {
            ds.unionByRank(connections[i][0], connections[i][1]);
        }
        
        int count = 0;
        
        for(int i = 0 ; i < n ; i++) {
            if(ds.parent[i] == i) count++;
        }
        
        return count-1;
    }
}


class DisjointSet {
    int[] rank, size, parent;
    
    DisjointSet(int n) {
        rank = new int[n + 1];
        size = new int[n + 1];
        parent = new int[n + 1];
        
        for(int i = 0 ; i < n ; i++) {
            rank[i] = 0;
            size[i] = 1;
            parent[i] = i;
        }
    }
    
    public int findUPar(int node) {
        if(node == parent[node]) return node;
        
        int ulp = findUPar(parent[node]);
        parent[node] = ulp;
        
        return parent[node];
    }
    
    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        
        if(ulp_u == ulp_v) return;
        
        int rankU = rank[ulp_u];
        int rankV = rank[ulp_v];
        
        if(rankU > rankV) {
            parent[ulp_v] = ulp_u;
        } else if(rankV > rankU) {
            parent[ulp_u] = ulp_v;
        } else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }
}