#include <bits/stdc++.h>
struct Q {
    vector<pair<long long, int>> q;
    void A(long long c, int u) {
        q.push_back({c, u});
        int i = q.size() - 1;
        while(i > 0) {
            int p = (i - 1) >> 2;
            if(q[i].first < q[p].first) {
                swap(q[i], q[p]);
                i = p;
            } else break;
        }
    }
    pair<long long, int> B() {
        auto r = q[0];
        q[0] = q.back();
        q.pop_back();
        int i = 0, n = q.size();
        while(1) {
            int m = i, c = (i << 2) + 1;
            for(int k = 0; k < 4 && c + k < n; ++k)
                if(q[c + k].first < q[m].first) m = c + k;
            if(m != i) {
                swap(q[i], q[m]);
                i = m;
            } else break;
        }
        return r;
    }
    bool C() { return q.empty(); }
};
void R(int u, long long c, int m, int n, const vector<vector<int>>& P, vector<long long>& D, Q& q) {
    int p = u & 1, idx = u >> 1, j = idx % n, i = idx / n;
    int wu = u ^ 1;
    long long wc = c + P[i][j];
    if (wc < D[wu]) D[wu] = wc, q.A(wc, wu);
    int dx[] = {0, 1, 0, -1}, dy[] = {1, 0, -1, 0};
    for(int d = 0; d < 4; ++d) {
        int ni = i + dx[d], nj = j + dy[d];
        if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
            long long nc = c + 1LL * (ni + 1) * (nj + 1);
            if (p == 0 ? (d >= 2) : (d < 2)) nc += P[i][j];
            int nu = ((ni * n + nj) << 1) | (p ^ 1);
            if (nc < D[nu]) D[nu] = nc, q.A(nc, nu);
        }
    }
}
class Solution {
public:
    long long minCost(int m, int n, vector<vector<int>>& P) {
        int sz = m * n * 2;
        vector<long long> D(sz, 4e18);
        Q q;
        D[0] = 1;
        q.A(1, 0);
        while(!q.C()) {
            auto [c, u] = q.B();
            if (c > D[u]) continue;
            if ((u >> 1) == m * n - 1) return c;
            R(u, c, m, n, P, D, q);
        }
        return -1;
    }
};