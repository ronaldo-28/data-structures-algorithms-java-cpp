class Solution {
public:
    int countWays(vector<int>& a) {
        int n = a.size();
        vector<int> has(n + 1), cnt(n + 1);
        for (int i : a) has[i] = 1, cnt[i]++;
        for (int i = 1; i <= n; i++) cnt[i] += cnt[i - 1];
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            if (!has[i] && cnt[i] == i) ans++;
        }
        return ans;
    }
};