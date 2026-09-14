class Solution {
public:
    void dfs(vector<pair<int, bool>> adj[], int i, int p, vector<int> &guessCount) {
        guessCount[i] = 0;
        for (auto &x: adj[i]) {
            if (x.first == p) continue;
            guessCount[i] += x.second;
            dfs(adj, x.first, i, guessCount);
            guessCount[i] += guessCount[x.first];
        }
    }
    void dfs2(vector<pair<int, bool>> adj[], int i, int p, vector<int> &guessCount) {
        for (auto &x: adj[i]) {
            if (x.first == p) continue;
            guessCount[x.first] = guessCount[i] - x.second;
            for (auto &y: adj[x.first]) {
                if (y.first == i && y.second)
                    guessCount[x.first]+=1;
            }
            dfs2(adj, x.first, i, guessCount);
        }
    }
    int rootCount(vector<vector<int>>& edges, vector<vector<int>>& guesses, int k) {
        int n = edges.size()+1;
        vector<pair<int, bool>> adj[n];
        vector<int> guessCount(n);
        for(auto &x: edges) {
            adj[x[0]].push_back({x[1],false});
            adj[x[1]].push_back({x[0],false});
        }

        for (const auto &x: guesses) {
            for (auto &y: adj[x[0]]) {
                if (y.first == x[1])
                    y.second = true;
            }
        }
        dfs(adj, 0, -1, guessCount);
        cout<<guessCount[0]<<endl;
        dfs2(adj, 0, -1, guessCount);
        int ans = 0;
        for (auto &x: guessCount) {
            if (x >= k)
                ans+=1;
        }
        return ans;
    }
};