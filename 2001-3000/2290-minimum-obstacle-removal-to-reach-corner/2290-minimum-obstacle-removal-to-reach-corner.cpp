class Solution {
public:
    int minimumObstacles(vector<vector<int>>& grid) {
        int n = grid.size(), m = grid[0].size();
        vector<int> dist(n * m, 1e9);
        dist[0] = 0;
         
        deque<int> dq; // for 0-1 BFS
        dq.push_front(0);
        
        int dirs[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while(!dq.empty()){
            int u = dq.front();
            dq.pop_front();
             
            int r = u / m;
            int c = u % m;
            
            for(auto& d : dirs){
                int nr = r + d[0];
                int nc = c + d[1]; 
                if(nr >= 0 && nr < n && nc >= 0 && nc < m){ 
                    int v = nr * m + nc;
                    int weight = grid[nr][nc];
                    
                    if(dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        
                        if(weight == 1) dq.push_back(v);
                        else dq.push_front(v);
                    }
                }
            } 
        }
         
        return dist.back();
    }
};