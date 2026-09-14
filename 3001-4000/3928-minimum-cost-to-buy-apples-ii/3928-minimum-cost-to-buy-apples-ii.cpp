class Solution {
public:
    using ull = unsigned long long;

    static constexpr ull INF = (ull)4e18;

    struct EdgeGraph {
        vector<int> head;
        vector<int> to;
        vector<int> nxt;
        vector<ull> emptyCost;
        vector<ull> appleCost;

        EdgeGraph(int n, int m) {
            head.assign(n, -1);
            to.reserve(2 * m);
            nxt.reserve(2 * m);
            emptyCost.reserve(2 * m);
            appleCost.reserve(2 * m);
        }

        void addEdge(int u, int v, ull c, ull t) {
            to.push_back(v);
            emptyCost.push_back(c);
            appleCost.push_back(c * t);
            nxt.push_back(head[u]);
            head[u] = (int)to.size() - 1;
        }
    };

    struct Item {
        ull key;
        int x;
        int y;
    };

    class RadixHeap {
    private:
        array<vector<Item>, 65> bucket;
        ull last = 0;
        int sz = 0;

        int bucketId(ull x) const {
            if (x == last) return 0;
            return 64 - __builtin_clzll(x ^ last);
        }

    public:
        bool empty() const {
            return sz == 0;
        }

        void push(ull key, int x, int y) {
            bucket[bucketId(key)].push_back({key, x, y});
            sz++;
        }

        Item pop() {
            if (bucket[0].empty()) {
                int id = 1;
                while (bucket[id].empty()) id++;

                ull newLast = bucket[id][0].key;

                for (const Item& item : bucket[id]) {
                    if (item.key < newLast) newLast = item.key;
                }

                last = newLast;

                for (const Item& item : bucket[id]) {
                    bucket[bucketId(item.key)].push_back(item);
                }

                bucket[id].clear();
            }

            Item res = bucket[0].back();
            bucket[0].pop_back();
            sz--;
            return res;
        }
    };

    vector<int> minCost(int n, vector<int>& prices, vector<vector<int>>& roads) {
        int m = roads.size();
        EdgeGraph g(n, m);

        for (auto& e : roads) {
            int u = e[0];
            int v = e[1];
            ull c = e[2];
            ull t = e[3];

            g.addEdge(u, v, c, t);
            g.addEdge(v, u, c, t);
        }

        int total = n * n;
        vector<ull> dist(total, INF);
        vector<int> ans(n, -1);
        RadixHeap pq;

        for (int i = 0; i < n; i++) {
            int id = i * n + i;
            dist[id] = prices[i];
            pq.push(prices[i], i, i);
        }

        int done = 0;

        while (!pq.empty() && done < n) {
            Item cur = pq.pop();

            int x = cur.x;
            int y = cur.y;
            int id = x * n + y;

            if (cur.key != dist[id]) continue;

            if (x == y && ans[x] == -1) {
                ans[x] = (int)cur.key;
                done++;
            }

            for (int e = g.head[x]; e != -1; e = g.nxt[e]) {
                int nx = g.to[e];
                int nid = nx * n + y;
                ull nd = cur.key + g.emptyCost[e];

                if (nd < dist[nid]) {
                    dist[nid] = nd;
                    pq.push(nd, nx, y);
                }
            }

            int row = x * n;

            for (int e = g.head[y]; e != -1; e = g.nxt[e]) {
                int ny = g.to[e];
                int nid = row + ny;
                ull nd = cur.key + g.appleCost[e];

                if (nd < dist[nid]) {
                    dist[nid] = nd;
                    pq.push(nd, x, ny);
                }
            }
        }

        return ans;
    }
};