class Solution {
public:
    int minOperations(vector<int>& nums, int k) {
        int n = nums.size();
        int minTime = 0;
        unordered_set<int> st;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] >= 1 && nums[i] <= k) {
                st.insert(nums[i]);
            }
            minTime++;
            if (st.size() == k)
                break;
        }
        return minTime;
    }
};