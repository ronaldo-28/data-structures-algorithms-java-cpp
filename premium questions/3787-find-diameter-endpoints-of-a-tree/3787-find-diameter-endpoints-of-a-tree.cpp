class Solution {
public:
    string findSpecialNodes(int n, vector<vector<int>>& edges) {
        string ans(n, '0');
        vector<vector<int>> adj(n);
        adj.reserve(n);

        for (auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        //DFS1 from 0 (arbitary point)
        vector<int> dist(n, -1);
        vector<int> st;
        st.reserve(n);

        dist[0] = 0;
        st.push_back(0);

        int mx1 = 0;
        int v = 0; // one farthest node from 0

        while (!st.empty()) {
            int u = st.back();
            st.pop_back();

            for (int to : adj[u]) {
                if (dist[to] != -1) continue;
                dist[to] = dist[u] + 1;
                st.push_back(to);

                if (dist[to] > mx1) {
                    mx1 = dist[to];
                    v = to;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == mx1) ans[i] = '1';
        }

        // DFS2 from v
        vector<int> dist2(n, -1);
        st.clear();

        dist2[v] = 0;
        st.push_back(v);

        int mx2 = 0;

        while (!st.empty()) {
            int u = st.back();
            st.pop_back();

            for (int to : adj[u]) {
                if (dist2[to] != -1) continue;
                dist2[to] = dist2[u] + 1;
                st.push_back(to);

                if (dist2[to] > mx2) {
                    mx2 = dist2[to];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist2[i] == mx2) ans[i] = '1';
        }

        return ans;
    }
};