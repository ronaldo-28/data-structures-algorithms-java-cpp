
#include <bits/stdc++.h>
using namespace std;

class Solution {
public:

    int minCost(int n, vector<vector<int>>& edges) {

        int m = edges.size();

        // Membuat graph menggunakan forward star
        vector<int> head(n, -1);
        vector<int> to(2 * m);
        vector<int> nxt(2 * m);
        vector<long long> cost(2 * m);

        int idx = 0;

        // Fungsi menambahkan edge
        auto addEdge = [&](int u, int v, long long w) {
            to[idx] = v;
            cost[idx] = w;
            nxt[idx] = head[u];
            head[u] = idx++;
        };

        // Membuat edge normal dan reversal
        for (auto &e : edges) {

            int u = e[0];
            int v = e[1];
            int w = e[2];

            addEdge(u, v, w);
            addEdge(v, u, 2LL * w);
        }

        // Inisialisasi Dijkstra
        const long long INF = LLONG_MAX;

        vector<long long> dist(n, INF);

        priority_queue<
            pair<long long, int>,
            vector<pair<long long, int>>,
            greater<pair<long long, int>>
        > pq;

        dist[0] = 0;
        pq.push({0, 0});

        // Proses Dijkstra
        while (!pq.empty()) {

            auto [d, u] = pq.top();
            pq.pop();

            // Skip node yang sudah memiliki jarak lebih baik
            if (d != dist[u]) {
                continue;
            }

            // Tujuan sudah ditemukan
            if (u == n - 1) {
                return (int)d;
            }

            // Mengecek seluruh tetangga
            for (int i = head[u]; i != -1; i = nxt[i]) {

                int v = to[i];
                long long w = cost[i];

                long long newDist = d + w;

                if (newDist < dist[v]) {

                    dist[v] = newDist;
                    pq.push({newDist, v});
                }
            }
        }

        return -1;
    }
};