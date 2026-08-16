class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int n = nums.size();

        if (x == 0)
            return 0;

        set<int> st;
        int ans = INT_MAX;

        for (int i = n - 1 - x; i >= 0; i--) {
            st.insert(nums[i + x]);

            auto it = st.lower_bound(nums[i]);

            if (it != st.end())
                ans = min(ans, abs(nums[i] - *it));

            if (it != st.begin()) {
                --it;
                ans = min(ans, abs(nums[i] - *it));
            }
        }

        return ans;
    }
};