class Solution {
public:
    int minimumOperations(vector<vector<int>>& g) {
    int m = g.size(), n = g[0].size(), dp[2][10] = {}, hi[2][2] = {};
    for (int i = 0; i < n; ++i) {
        int cnt[10] = {}, c = i % 2, p = !c; // current and previos column.
        for (int j = 0; j < m; ++j)
            ++cnt[g[j][i]];
        for (int v = 0; v < 10; ++v) {
            dp[c][v] = cnt[v] + (dp[p][v] == hi[p][0] ? hi[p][1] : hi[p][0]);
            if (hi[c][0] < dp[c][v])
                hi[c][1] = exchange(hi[c][0], dp[c][v]);
            else if (hi[c][1] < dp[c][v])
                hi[c][1] = dp[c][v];
        }
    }
    return n * m - hi[!(n % 2)][0];
}
};