
class Solution {
public:
    int sumOfGoodSubsequences(vector<int>& nums) {
        int mod = 1e9+7;
        int n = nums.size();
        int mx = *max_element(nums.begin(),nums.end());
        vector<long long> sum(mx+5),dp(mx+5),count(mx+5);
        for(int i=0;i<n;++i){
            if(nums[i] == 0){ // --- error ---
            dp[nums[i]] += (1+dp[nums[i]+1]);
            sum[nums[i]] += (nums[i]*(1+dp[nums[i]+1]) + sum[nums[i]+1]);
            continue;
            }
            dp[nums[i]] =(dp[nums[i]]+ (1+dp[nums[i]-1]+dp[nums[i]+1]))%mod;
            int x = nums[i];
            sum[nums[i]] = (sum[nums[i]]+((((1+dp[nums[i]-1]+dp[nums[i]+1])*x) + sum[nums[i]-1])+ sum[nums[i]+1]))%mod;


        }
        int ans = 0;
        for(int i=0;i<=mx;++i){
            ans = (ans+sum[i])%mod;
        }
        return ans;
    }
};