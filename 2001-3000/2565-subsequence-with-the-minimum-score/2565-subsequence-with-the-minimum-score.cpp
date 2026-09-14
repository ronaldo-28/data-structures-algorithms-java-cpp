class Solution {
public:
    int minimumScore(string s, string t) {
    int ss = s.size(), st = t.size(), k = st - 1;
    vector<int> dp(st, -1);
    for (int i = ss - 1; i >= 0 && k >= 0; --i)
        if (s[i] == t[k])
            dp[k--] = i;
    int res = k + 1;
    for (int i = 0, j = 0; i < ss && j < st && res > 0; ++i)
        if (s[i] == t[j]) {
            for (; k < t.size() && dp[k] <= i; ++k);
            res = min(res, k - (++j));
        }
    return res;
}
};