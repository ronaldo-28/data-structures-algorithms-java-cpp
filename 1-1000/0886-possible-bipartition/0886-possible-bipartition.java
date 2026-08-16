class Solution {
    
    private  int[] edge, next, head;
    int index;   
    
    void add(int u, int v) {
        edge[index] = v;
        next[index] = head[u];
        head[u] = index++;
    }
        
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] group = new int[n+1];
        
        int m = dislikes.length;
        edge = new int[m << 1];
        next = new int[m << 1];
        head = new int[n + 1];

        for(int i = 0; i <= n; ++i)
            head[i] = -1;
        index = 0;
        for (int[] dislike : dislikes) {
            int u = dislike[0], v = dislike[1];
            add(u, v);
            add(v, u);
        }        

        for(int i = 1; i <= n; ++i){
            if(group[i] == 0){
               if(!dfs(i, 1, group))
                  return false;
            }
        }
        
        return true;
    }

    public boolean dfs(int u, int g, int[] group) {
        group[u] = g;
       for (int index = head[u]; index != -1; index = next[index]) {
                
            int v = edge[index];
            if(group[v] == 0){
                if(!dfs(v, g ^ 3, group)) return false;
            }else if(group[v] == g){
                return false;
            }
             
        }
        return true;
    }
}