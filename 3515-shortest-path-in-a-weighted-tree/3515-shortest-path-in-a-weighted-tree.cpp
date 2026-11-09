class BIT {
public:
    vector<int> tree;
    BIT(int n) {
        tree.resize(n+1);
    }
    void update(int index, int val) {
        for (int i = index+1; i < tree.size(); i += i & -i) {
            tree[i] += val;
        }
    }
    int query(int index) {
        int sum = 0;
        for (int i = index+1; i > 0; i &= (i-1)) {
            sum += tree[i];
        }
        return sum;
    }
};
class Solution {
public:
    vector<int> treeQueries(int n, vector<vector<int>>& edges, vector<vector<int>>& queries) {
        vector<vector<pair<int, int>>> graph(n+1);
        for (auto& e : edges) {
            graph[e[0]].emplace_back(e[1], e[2]);
        }
        vector<int> in(n+1);
        vector<int> out(n+1);
        vector<int> w(n+1);
        BIT tree(n+1);
        int time = 0;
        auto dfs = [&](this auto&& dfs, int node, int pa) -> void {
            in[node] = ++time;
            for (auto [next, weight] : graph[node]) {
                if (next != pa) {
                    w[next] = weight;
                    dfs(next, node);
                }
            }
            out[node] = time;
        };
        dfs(1, -1);
        for (int node = 1; node <= n; node++) {
            tree.update(in[node], w[node]);
            tree.update(out[node]+1, -w[node]);
        }
        vector<int> ans;
        auto update = [&](int u, int v, int new_weight) {
            if (in[v] < in[u]) {
                swap(u, v);
            }
            int diff = new_weight - w[v];
            w[v] = new_weight;
            tree.update(in[v], diff);
            tree.update(out[v]+1, -diff);
        };
        for (auto& q : queries) {
            if (q[0] == 1) {
                update(q[1], q[2], q[3]);
            } else {
                ans.push_back(tree.query(in[q[1]]));
            }
        }
        return ans;
    }
};