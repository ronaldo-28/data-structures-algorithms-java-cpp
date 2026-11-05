class Solution {
const int INF = -1e9;
int dp[2][2001]; // k (row optimized), n
public:
    int maxSum(vector<int>& nums, int k, int m) {
        int n = nums.size(), res = INF, row = 0;
        for(int i = 1; i < n; i++) nums[i] += nums[i - 1];
        for(int cnt = 1; cnt <= k; cnt++, row ^= 1){
            for(int i = 0; i <= n; i++) dp[row][i] = INF;
            int best = INF;
            for(int i = cnt * m; i + (k - cnt) * m <= n; i++){  // 1 based
                best = max(best, dp[row^1][i - m]);
                // try [0 ... i-m] as previous ((cnt-1)-th) segment endpoint and [i-m+1...i] segment
                int windowSum = i > m ? nums[i - 1] - nums[i - m - 1] : nums[i - 1];
                dp[row][i] = best + windowSum;
                // try to expand cnt-th range
                if(i > cnt * m) dp[row][i] = max(dp[row][i], nums[i - 1] - nums[i - 2] + dp[row][i - 1]);
                if(cnt == k) res = max(res, dp[row][i]);
            }
        }
        return res;
    }
};