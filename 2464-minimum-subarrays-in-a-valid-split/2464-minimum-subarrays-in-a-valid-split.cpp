class Solution {
private:
    const int INF = 1e9;
    
    vector<int> getFactors(int x) {
        vector<int> factors;

        for (int p = 2; p * p <= x; p++) {
            if (x % p == 0) {
                factors.push_back(p);

                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        if (x > 1) {
            factors.push_back(x);
        }

        return factors;
    }

public:
    int validSubarraySplit(vector<int>& nums) {
        int size = nums.size();

        vector<int> dp(size, INF);
        unordered_map<int, int> map;

        for (int i = 0; i < size; i++) {
            vector<int> factors = getFactors(nums[i]);

            int prevCost = (i == 0 ? 0 : dp[i - 1]);

            // nums[i] can be the start of a future subarray
            for (int p : factors) {
                if (!map.count(p)) {
                    map[p] = prevCost;
                } else {
                    map[p] = min(map[p], prevCost);
                }
            }

            // nums[i] is the end of current subarray
            for (int p : factors) {
                dp[i] = min(dp[i], map[p] + 1);
            }
        }

        return dp[size - 1] == INF ? -1 : dp[size - 1];
    }
};