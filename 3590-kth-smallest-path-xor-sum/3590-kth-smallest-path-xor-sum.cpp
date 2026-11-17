class Solution {
public:
    vector<int> bit;
    int uniqueCnt;

    void add(int ind, int val) {
        for (; ind <= uniqueCnt; ind += ind & -ind) {
            bit[ind] += val;
        }
    }

    vector<int> kthSmallest(vector<int>& par, vector<int>& vals, vector<vector<int>>& queries) {
        int n = par.size();

        vector<vector<int>> adj(n);
        for (int i = 1; i < n; i++) {
            adj[par[i]].push_back(i);
        }

        vector<int> pathXor(n);
        pathXor[0] = vals[0];

        auto dfs_xor = [&](auto& self, int u) -> void {
            for (int v : adj[u]) {
                pathXor[v] = pathXor[u] ^ vals[v];
                self(self, v);
            }
        };
        dfs_xor(dfs_xor, 0);

        vector<int> unique_xor = pathXor;
        sort(unique_xor.begin(), unique_xor.end());
        unique_xor.erase(unique(unique_xor.begin(), unique_xor.end()), unique_xor.end());

        uniqueCnt = unique_xor.size();

        vector<int> rnk(n);
        for (int i = 0; i < n; i++) {
            rnk[i] = lower_bound(unique_xor.begin(), unique_xor.end(), pathXor[i]) - unique_xor.begin() + 1;
        }

        vector<int> in(n), out(n), rev(n + 1);
        vector<int> sz(n, 1), heavy(n, -1);
        int timer = 0;

        auto dfs_sz = [&](auto& self, int u) -> void {
            in[u] = ++timer;
            rev[timer] = u;

            for (int v : adj[u]) {
                self(self, v);
                sz[u] += sz[v];

                if (heavy[u] == -1 || sz[v] > sz[heavy[u]]) {
                    heavy[u] = v;
                }
            }
            out[u] = timer;
        };
        dfs_sz(dfs_sz, 0);

        int q = queries.size();
        vector<vector<pair<int, int>>> node_queries(n);
        for (int i = 0; i < q; i++) {
            node_queries[queries[i][0]].push_back({queries[i][1], i});
        }

        vector<int> freq(uniqueCnt + 1, 0);
        bit.assign(uniqueCnt + 1, 0);
        int distinct_count = 0;

        auto add_node = [&](int u, int delta) {
            int r = rnk[u];
            if (delta == 1) {
                if (freq[r] == 0) {
                    distinct_count++;
                    add(r, 1);
                }
                freq[r]++;
            } else {
                freq[r]--;
                if (freq[r] == 0) {
                    add(r, -1);
                    distinct_count--;
                }
            }
        };

        auto get_kth = [&](int k) -> int {
            if (k > distinct_count) return -1;

            int idx = 0;
            for (int i = 19; i >= 0; i--) {
                int next_idx = idx + (1 << i);
                if (next_idx <= uniqueCnt && bit[next_idx] < k) {
                    idx = next_idx;
                    k -= bit[idx];
                }
            }
            return unique_xor[idx];
        };

        vector<int> ans(q, -1);

        auto solve = [&](auto& self, int u, bool keep) -> void {
            for (int v : adj[u]) {
                if (v != heavy[u]) self(self, v, false);
            }
            if (heavy[u] != -1) self(self, heavy[u], true);

            add_node(u, 1);
            for (int v : adj[u]) {
                if (v != heavy[u]) {
                    for (int i = in[v]; i <= out[v]; i++) {
                        add_node(rev[i], 1);
                    }
                }
            }

            for (auto& query : node_queries[u]) {
                ans[query.second] = get_kth(query.first);
            }

            if (!keep) {
                for (int i = in[u]; i <= out[u]; i++) {
                    add_node(rev[i], -1);
                }
            }
        };
        solve(solve, 0, true);
        return ans;
    }
};