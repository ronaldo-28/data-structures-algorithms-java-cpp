class Solution {
public:
    int minOperations(vector<int>& nums) {
        int cnt = count(nums.begin(), nums.end(), nums[0]);
        return cnt < nums.size() ? 1 : 0;
    }
};