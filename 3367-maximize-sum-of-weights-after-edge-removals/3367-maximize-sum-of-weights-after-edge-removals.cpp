vector<vector<pair<int,int>>>graph;
vector<long long> takeparent;
vector<long long> nottake;
class Solution {
public:
    void dfs(int u,int par,int k){
        long long total = 0;
        vector<int> gains;
        for(auto [v,w] : graph[u]){
            if(v == par) continue;
            dfs(v,u,k);
            total += max(takeparent[v]+w,nottake[v]);
            if(takeparent[v]+w > nottake[v]){
                gains.push_back(takeparent[v] + w-nottake[v]);
            }
        }
        int kept = gains.size();
        sort(gains.begin(),gains.end());//sabse chote hataenge na
        long long temp = total;
        for(int i=0;i<kept-k;i++){
            total -= gains[i];
        }
        nottake[u] = total;
        for(int i=0;i<kept-k+1;i++){
            temp -= gains[i];
        }
        takeparent[u] = temp;
    }
    long long maximizeSumOfWeights(vector<vector<int>>& edges, int k) {
        int n = edges.size() + 1;
        graph.assign(n,{});
        nottake.assign(n,0);
        takeparent.assign(n,0);
        for(int i = 0;i<n-1;i++){
            int a = edges[i][0];
            int b = edges[i][1];
            int w = edges[i][2];
            graph[a].push_back({b,w});
            graph[b].push_back({a,w});
        }
        dfs(0,-1,k);
        return nottake[0];
    }
};