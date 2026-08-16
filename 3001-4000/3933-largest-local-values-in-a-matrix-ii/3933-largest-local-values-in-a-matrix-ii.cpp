class Solution {
public:
    int countLocalMaximums(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();
        int ans = 0;
        vector<pair<int, int>> pos[201];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pos[matrix[i][j]].push_back({i, j});
            }
        }
        vector<int> gt(201, 0);
        int s = 0;
        for (int v = 200; v >= 0; v--) {
            gt[v] = s;
            s += pos[v].size();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = matrix[i][j];
                if (x == 0) continue;

                if (gt[x] == 0) {
                    ans++;
                    continue;
                }

                int r1 = max(0, i - x), r2 = min(n - 1, i + x);
                int c1 = max(0, j - x), c2 = min(m - 1, j + x);
                int a = (r2 - r1 + 1) * (c2 - c1 + 1);
                bool ok = true;

                if (gt[x] < a) {
                    for (int v = x + 1; v <= 200; v++) {
                        for (auto& p : pos[v]) {
                            int k = p.first, l = p.second;
                            if (k >= r1 && k <= r2 && l >= c1 && l <= c2) {
                                if (abs(k - i) == x && abs(l - j) == x)
                                    continue;
                                ok = false;
                                break;
                            }
                        }
                        if (!ok) break;
                    }
                } else {
                    for (int d = 1; d <= x; d++) {
                        int sl = (d == x) ? j - d + 1 : j - d;
                        int el = (d == x) ? j + d - 1 : j + d;

                        int k = i - d;
                        if (k >= 0 && k < n) {
                            for (int l = sl; l <= el; l++) {
                                if (l >= 0 && l < m && matrix[k][l] > x) {
                                    ok = false;
                                    break;
                                }
                            }
                        }
                        if (!ok) break;

                        k = i + d;
                        if (k >= 0 && k < n) {
                            for (int l = sl; l <= el; l++) {
                                if (l >= 0 && l < m && matrix[k][l] > x) {
                                    ok = false;
                                    break;
                                }
                            }
                        }
                        if (!ok) break;

                        int l = j - d;
                        if (l >= 0 && l < m) {
                            for (int r = i - d + 1; r <= i + d - 1; r++) {
                                if (r >= 0 && r < n && matrix[r][l] > x) {
                                    ok = false;
                                    break;
                                }
                            }
                        }
                        if (!ok) break;

                        l = j + d;
                        if (l >= 0 && l < m) {
                            for (int r = i - d + 1; r <= i + d - 1; r++) {
                                if (r >= 0 && r < n && matrix[r][l] > x) {
                                    ok = false;
                                    break;
                                }
                            }
                        }
                        if (!ok) break;
                    }
                }
                if (ok) ans++;
            }
        }
        return ans;
    }
};