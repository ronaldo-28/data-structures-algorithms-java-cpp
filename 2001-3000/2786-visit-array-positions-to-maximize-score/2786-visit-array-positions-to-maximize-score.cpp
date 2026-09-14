class Solution {
public:
    long long maxScore(vector<int>& nums, int x) {
        int n = nums.size();
        long long NEG = LLONG_MIN / 2;
        long long even = NEG, odd = NEG;
        if (nums[0] % 2 == 0) even = nums[0];
        else odd = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == 0) {
                long long best = max(even + nums[i], odd - x + nums[i]);
                even = max(even, best);
            } else {
                long long best = max(odd + nums[i], even - x + nums[i]);
                odd = max(odd, best);
            }
        }
        return max(even, odd);
    }
};