const int MOD = 1e9+7;
class Solution {
public:
    int minWastedSpace(vector<int>& packages, vector<vector<int>>& boxes) {
        vector<long long> sum(packages.size() + 1);
        sum[0] = 0;
        for (int i = 0; i < packages.size(); ++i) {
            sum[i+1] = sum[i] + packages[i];
        }
        vector<int> count(1e5+5, 0);
        for (int p : packages) ++count[p];
        for (int i = 1; i < count.size(); ++i) count[i] += count[i-1];
        long long mn = 1e17;
        for (vector<int> &box : boxes) {
            sort(box.begin(), box.end());
            long long tot = 0;
            long long prev_cnt = 0;
            for (int b : box) {
                long long cnt = count[b];
                long long total_space = (cnt - prev_cnt) * b;
                long long used = sum[cnt] - sum[prev_cnt];
                prev_cnt = cnt;
                tot += total_space - used;
            }
            if (prev_cnt == packages.size()) {
                mn = min(mn, tot);
            }
        }
        if (mn == 1e17) {
            return -1;
        }
        return mn % MOD;
    }
};