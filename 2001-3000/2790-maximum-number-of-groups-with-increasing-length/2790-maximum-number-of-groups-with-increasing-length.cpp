class Solution {
public:
    int maxIncreasingGroups(vector<int>& usageLimits) {
        const int n = usageLimits.size();
        vector<int> cnt(n+1);
        for (const int limit : usageLimits)
            ++cnt[min(n, limit)];
        long long total = 0, numGroups = 0;
        for (int limit = 1; limit <= n; ++limit) {
            for (int rem = cnt[limit]; rem > 0; --rem) {
                total += limit;
                const auto triTotal = (numGroups+1) * (numGroups+2) / 2;
                numGroups += (total >= triTotal);
            }
        }
        return numGroups;
    }
};