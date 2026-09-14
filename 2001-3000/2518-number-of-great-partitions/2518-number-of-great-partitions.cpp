class Solution {
public:
    int countPartitions(vector<int>& nums, int k) {
        const int MOD = 1e9 + 7;

        long long total = 0;
        for (int x : nums) total += x;

        if (total < 2LL * k) return 0;

        int n = nums.size();

        vector<long long> dp(k, 0);
        dp[0] = 1;

        for (int x : nums) {
            for (int s = k - 1; s >= x; --s) {
                dp[s] = (dp[s] + dp[s - x]) % MOD;
            }
        }

        long long bad = 0;
        for (int s = 0; s < k; ++s) {
            bad = (bad + dp[s]) % MOD;
        }

        long long totalPartitions = 1;
        for (int i = 0; i < n; ++i) {
            totalPartitions = (totalPartitions * 2) % MOD;
        }

        long long ans = (totalPartitions - 2 * bad) % MOD;
        if (ans < 0) ans += MOD;

        return (int)ans;
    }
};