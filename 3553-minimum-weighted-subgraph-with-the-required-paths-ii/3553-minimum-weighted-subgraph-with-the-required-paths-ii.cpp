class Solution {
public:
    vector<int> minimumWeight(vector<vector<int>>& edges, vector<vector<int>>& queries) {
        int n = (int)edges.size() + 1;
        vector<int> head(n, -1), nxt(2 * (n - 1)), to(2 * (n - 1)), wt(2 * (n - 1));
        for (int i = 0; i < n - 1; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            to[2*i] = v; wt[2*i] = w; nxt[2*i] = head[u]; head[u] = 2*i;
            to[2*i+1] = u; wt[2*i+1] = w; nxt[2*i+1] = head[v]; head[v] = 2*i+1;
        }
        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        vector<vector<int>> up(LOG, vector<int>(n, 0));
        vector<int> depth(n, 0);
        vector<long long> f(n, 0);
        vector<int> order; order.reserve(n);
        vector<char> vis(n, 0);
        order.push_back(0); vis[0] = 1;
        for (size_t i = 0; i < order.size(); i++) {
            int u = order[i];
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (!vis[v]) {
                    vis[v] = 1;
                    up[0][v] = u;
                    depth[v] = depth[u] + 1;
                    f[v] = f[u] + wt[e];
                    order.push_back(v);
                }
            }
        }
        for (int k = 1; k < LOG; k++)
            for (int v = 0; v < n; v++)
                up[k][v] = up[k-1][up[k-1][v]];
        auto lca = [&](int u, int v) -> int {
            if (depth[u] < depth[v]) swap(u, v);
            int diff = depth[u] - depth[v];
            for (int k = 0; k < LOG; k++) if ((diff >> k) & 1) u = up[k][u];
            if (u == v) return u;
            for (int k = LOG - 1; k >= 0; k--)
                if (up[k][u] != up[k][v]) { u = up[k][u]; v = up[k][v]; }
            return up[0][u];
        };
        auto dist = [&](int u, int v) -> long long {
            int w = lca(u, v);
            return f[u] + f[v] - 2 * f[w];
        };
        vector<int> ans;
        ans.reserve(queries.size());
        for (auto& q : queries) {
            long long total = dist(q[0], q[1]) + dist(q[1], q[2]) + dist(q[2], q[0]);
            ans.push_back((int)(total / 2));
        }
        return ans;
    }
};