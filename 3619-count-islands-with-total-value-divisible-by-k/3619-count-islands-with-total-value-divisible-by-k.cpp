int p[100000], sz[100000], row[1000];
long long  v[100000];
int repr(int i) { return p[i] == i ? i : p[i] = repr(p[i]); }
int merge(int i, int j) {
    if (i == j) return i;
    if (sz[i] < sz[j]) swap(i, j); else if (sz[i] == sz[j]) ++sz[i];
    p[j] = i; v[i] += v[j];
    return i;
}

class Solution {
public:
    int countIslands(vector<vector<int>>& grid, int k) {
        int N = size(grid), M = size(grid[0]), nx = 0;
        for (int j = 0, pr = -1; j < M; ++j) {
            if (int g = grid[0][j]; g > 0) {
                if (pr < 0) pr = nx++, p[pr] = pr, sz[pr] = 1, v[pr] = 0;
                v[pr] += g;
            } else pr = -1;
            row[j] = pr;
        }

        for (int i = 1; i < N; ++i) {
            int pr = row[0] >= 0 ? repr(row[0]) : -1;
            if (int g = grid[i][0]; g > 0) {
                if (pr < 0) pr = nx++, p[pr] = pr, sz[pr] = 1, v[pr] = 0;
                v[pr] += g;
            } else pr = -1;
            row[0] = pr;
            for (int j = 1; j < M; ++j) {
                if (int g = grid[i][j]; g > 0) {
                    if (pr < 0) {
                        if (row[j] >= 0) pr = repr(row[j]); else pr = nx++, p[pr] = pr, sz[pr] = 1, v[pr] = 0;
                    }
                    else if (row[j] >= 0) pr = merge(pr, repr(row[j]));
                    v[pr] += g;
                } else pr = -1;
                row[j] = pr;
            }
        }

        int cnt = 0;
        for (int i = 0; i < nx; ++i) if (p[i] == i && v[i] % k == 0) ++cnt;
        return cnt;
    }
};