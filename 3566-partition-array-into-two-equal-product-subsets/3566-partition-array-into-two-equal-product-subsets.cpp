class Solution {
    bool dfs(const std::vector<int>& nums, int i,
             long long a, long long b, bool hasB) {
        if (i == static_cast<int>(nums.size())) {
            return a == 1 && b == 1 && hasB;
        }

        int x = nums[i];

        if (a % x == 0 && dfs(nums, i + 1, a / x, b, hasB)) {
            return true;
        }

        if (b % x == 0 && dfs(nums, i + 1, a, b / x, true)) {
            return true;
        }

        return false;
    }

public:
    bool checkEqualPartitions(std::vector<int>& nums, long long target) {
        for (int x : nums) {
            if (target % x != 0) {
                return false;
            }
        }

        return dfs(nums, 1, target / nums[0], target, false);
    }
};