class Solution {
public:
    int countDistinctStrings(string s, int k) {
        int res = 1, n = s.size(), MOD = 1e9+7;
        for (int i = 0; i + k - 1 < n; i++)
            res = (1LL * res * 2) % MOD;
        return res;
    }
};