class Solution {
public:
    int minimumSemesters(int n, vector<vector<int>>& edges) {
        vector<int> g[n];
        vector<int> ind(n, 0);

        for(auto &edge: edges){
            g[edge[0]-1].push_back(edge[1]-1);
            ind[edge[1]-1]++;
        }

        queue<int> q;
        vector<bool> vis(n, false);
        for(int i=0;i<n;i++){
            if(ind[i] == 0){
                q.push(i);
                vis[i] = true;
            }
        }
        
        int sems = 0;

        while(!q.empty()){
            int sz = q.size();
            sems++;

            while(sz-- > 0){
                int u = q.front(); q.pop();
                for(auto v: g[u]){
                    ind[v]--;
                    if(ind[v] == 0){
                        vis[v] = true;
                        q.push(v);
                    }
                }
            }
        }

        for(int i=0;i<n;i++){
            if(!vis[i]) return -1;
        }

        return sems;
    }
};