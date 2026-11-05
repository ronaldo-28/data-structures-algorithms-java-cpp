class Solution {
public:
    int minCost(vector<int>& nums) {
        int n = size(nums);
        vector<int> dp(n, INT_MAX);
        dp[0] = 0;
        for (int i = 1; i < n-1; i += 2) {
            for (int j = 0; j < i; ++j) {
                dp[i] = min(dp[i], dp[j] + max(nums[j], nums[i+1]));
                dp[i+1] = min(dp[i+1], dp[j] + max(nums[j], nums[i]));
                dp[j] += max(nums[i], nums[i+1]);
            }
        }
        int ans = INT_MAX;
        for (int i = 0; i < (n-1 | 1); ++i)
            ans = min(ans, dp[i] + max(nums[i], n % 2 == 0 ? nums[n-1] : 0));
        return ans;
    }
};