class Solution {
public:
    int maxKDivisibleComponents(int n, vector<vector<int>>& edges, vector<int>& values, int k) {
        if (n==1) return 1;
        vector<int> adj(n), idg(n);

        for (auto& e : edges) {
            adj[e[0]] ^= e[1];
            adj[e[1]] ^= e[0];
            idg[e[0]]++; idg[e[1]]++;
        }

        queue<int> q;

        for (int i = 0; i <n; i++){
            if (idg[i] == 1) q.emplace(i);
        }

        int res = 0;
        while(!q.empty()) {
            int cur = q.front(); q.pop();
            int nei = adj[cur];
            adj[nei] ^= cur;
            if (--idg[nei] == 1) q.emplace(nei);
            values[cur] %= k;
            if (!values[cur]) res++;
            values[nei] += values[cur];
            values[nei] %=k;
        }

        return res;
    }
};