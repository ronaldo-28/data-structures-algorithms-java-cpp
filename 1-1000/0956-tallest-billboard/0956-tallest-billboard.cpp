class Solution {
public:    
    int tallestBillboard(vector<int>& rods) {
        int total = 0;
        static int dp[5001], temp[5001];
        for(int i = 1; i < 5001; ++i) dp[i] = INT_MIN;
        dp[0] = 0;
        for(int j = 0, n = rods.size(); j < n; ++j){
            int r = rods[j];
            total += r;
            for(int i = 0; i <= total; ++i) temp[i] = dp[i];
            for(int diff = 0; diff + r <= total; ++diff){
                int dp1 = diff + r, dp2 = abs(diff - r);
                dp[dp1] = max(dp[dp1] , temp[diff]);
                dp[dp2] = max(dp[dp2] , temp[diff] + min(diff , r));
            }
        }
        return dp[0];
    }
};