class Solution {
public:
    long long countExcellentPairs(vector<int>& nums, int k) {

        sort(nums.begin(), nums.end());
        nums.erase(unique(nums.begin(), nums.end()), nums.end());

        vector<long long> freq(33, 0);

        for (int x : nums) {
            freq[__builtin_popcount(x)]++;
        }

        long long ans = 0;

        for (int i = 0; i <= 32; i++) {
            for (int j = 0; j <= 32; j++) {

                if (i + j >= k) {
                    ans += freq[i] * freq[j];
                }
            }
        }

        return ans;
    }
};