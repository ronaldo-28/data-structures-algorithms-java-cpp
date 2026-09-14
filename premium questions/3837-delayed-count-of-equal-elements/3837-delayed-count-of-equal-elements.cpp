class Solution {
public:
    vector<int> delayedCount(vector<int>& nums, int k) {
        unordered_map<int, int> count{};
        vector<int> ans(nums.size(), 0);

        for (int i{(int)nums.size() - k - 2}; i >= 0; i--) {
            count[nums[i + k + 1]]++;
            if (count.contains(nums[i])) 
                ans[i] = count[nums[i]];
        }

        return ans; 
    }
};