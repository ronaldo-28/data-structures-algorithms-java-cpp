class Solution {
public:
    vector<int> minimumTime(int n, vector<vector<int>>& edges, vector<int>& disappear) {
        priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>>pq;
        vector<vector<pair<int,int>>>adj(n);
        for(auto&it:edges){
            int u = it[0];
            int v = it[1];
            int w = it[2];
            adj[u].push_back({v,w});
            adj[v].push_back({u,w});
        }
        vector<int>dist(n,1e8);
        dist[0] = 0;
        pq.push({0,0});
        while(!pq.empty()){
            auto it = pq.top();
            pq.pop();
            if(it.first!=dist[it.second]) continue;
            for(auto&itt :adj[it.second]){
                int newNode= itt.first;
                int wt = itt.second;
                if(it.first + wt >= disappear[newNode]) continue;
                if(it.first+wt<dist[newNode]){
                    dist[newNode]= it.first+ wt;
                    pq.push({dist[newNode],newNode});
                }
            }
        }
    for(int i =0;i<n;i++){
        if(dist[i]==1e8) dist[i]= -1;
    }
    return dist;
    }
};