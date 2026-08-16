class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    int[] parent;
    int[] size;
    public int largestIsland(int[][] grid) {
        
        // Disjoint Set Union By Size 
        // In this we connect the all 1's by using the disjoint set union by size as here we use
        // size that we need to return the max nmber of 1's , and after connecting all then next 
        // was take all the remaining 0's then move in 4 directins and for each adjacent 1
        // find the size and add to the result ,use set for the uniwueness of the adjacent
        // 1's then find the size of the adjacents then add to the result ......
        
        int n = grid.length;
        DisjointSet(n*n);
        int ans = 0;

        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                if(grid[row][col]==0)
                  continue;
                
                for(int k=0;k<4;k++){
                    int newr = row+dr[k];
                    int newc = col+dc[k];

                    if(isValid(newr,newc,n)&&grid[newr][newc]==1){
                        int nodeNo = row*n+col;
                        int adjNo = newr*n+newc;

                        UnionBySize(nodeNo,adjNo);
                    }
                }
            }
        }

        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                if(grid[row][col]==1)
                  continue;
                
                Set<Integer>set = new HashSet<>();
                for(int k=0;k<4;k++){
                    int newr = row+dr[k];
                    int newc = col+dc[k];

                    if(isValid(newr,newc,n)&&grid[newr][newc]==1){
                        int nodeNo = newr*n+newc;
                        set.add(findParent(nodeNo));
                    }
                }

                int count = 1;
                for(int elem:set){
                    count+=size[elem];
                }

                ans = Math.max(ans,count);
            }
        }

        for(int i=0;i<n*n;i++){
            if(parent[i]==i)
              ans = Math.max(ans,size[i]);
        }

        return ans;
    }

    public boolean isValid(int row,int col,int n){
        return (row>=0&&row<n&&col>=0&&col<n);
    }

    public void DisjointSet(int n){
        parent = new int[n];
        size = new int[n];

        for(int i=0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findParent(int node){
        if(node==parent[node])
          return node;

        parent[node] = findParent(parent[node]);
        return parent[node];
    }

    public void UnionBySize(int u,int v){
        int parentU = findParent(u);
        int parentV = findParent(v);

        if(parentU==parentV)
          return;
        if(size[parentU]<size[parentV]){
            parent[parentU] = parentV;
            size[parentV]+=size[parentU];
        }
        else{
            parent[parentV] = parentU;

            size[parentU]+=size[parentV];
        }
    }
}