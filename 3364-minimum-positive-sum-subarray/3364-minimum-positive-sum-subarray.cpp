class Solution {
public:
    int minimumSumSubarray(vector<int>& nums, int l, int r) {
        int n = nums.size();
        int mini = INT_MAX;

        for (int size = l; size <= r; size++) {
            if (size > n) {
                break;
            }

            int sum = 0;

            
            for (int i = 0; i < size; i++) {
                sum += nums[i];
            }

            if (sum > 0) {
                mini = min(mini, sum);
            }

            
            for (int i = size; i < n; i++) {
                sum = sum - nums[i - size] + nums[i];

                if (sum > 0) {
                    mini = min(mini, sum);
                }
            }
        }

        return mini == INT_MAX ? -1 : mini;
    }
};