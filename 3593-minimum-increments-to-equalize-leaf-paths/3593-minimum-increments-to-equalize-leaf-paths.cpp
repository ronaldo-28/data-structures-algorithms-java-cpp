class Solution {
public:
    int minIncrease(int n, std::vector<std::vector<int>>& edges, std::vector<int>& cost) {
        std::vector<int> head(n, -1), nxt(2 * (n - 1)), to(2 * (n - 1));
        int idx = 0;
        for (const auto& e : edges) {
            to[idx] = e[1]; nxt[idx] = head[e[0]]; head[e[0]] = idx++;
            to[idx] = e[0]; nxt[idx] = head[e[1]]; head[e[1]] = idx++;
        }
        std::vector<int> order, parent(n, -1);
        order.reserve(n);
        order.push_back(0);
        parent[0] = 0;
        for (int i = 0; i < (int)order.size(); ++i) {
            int u = order[i];
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (v != parent[u] || (u == 0 && v != 0 && parent[v] == -1)) {
                    if (parent[v] == -1 && v != 0) {
                        parent[v] = u;
                        order.push_back(v);
                    }
                }
            }
        }
        std::vector<long long> best(n, 0), mx(n, -1);
        std::vector<int> cntMax(n, 0), childCnt(n, 0);
        for (int i = n - 1; i >= 0; --i) {
            int u = order[i];
            long long m = mx[u] < 0 ? 0 : mx[u];
            best[u] = m + cost[u];
            if (u == 0) break;
            int p = parent[u];
            ++childCnt[p];
            if (best[u] > mx[p]) {
                mx[p] = best[u];
                cntMax[p] = 1;
            } else if (best[u] == mx[p]) {
                ++cntMax[p];
            }
        }
        int ans = 0;
        for (int u = 0; u < n; ++u) {
            ans += childCnt[u] - cntMax[u];
        }
        return ans;
    }
};