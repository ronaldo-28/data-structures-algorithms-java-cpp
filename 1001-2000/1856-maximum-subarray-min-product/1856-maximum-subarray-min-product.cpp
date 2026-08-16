class Solution {
public:
    int maxSumMinProduct(vector<int>& nums) {
        int size = nums.size();
        long long mod = 1e9 + 7;

        vector<long long> prefix(size+1, 0);
        for(int i=0;i<size;i++)
        {
            prefix[i+1] = prefix[i] + nums[i];
        }

        vector<int> st;
        long long max_product = 0;
        for(int i=0;i<=size;i++)
        {
            long long curr_val = (i==size) ? 0 : nums[i];
            while(!st.empty() && nums[st.back()] > curr_val)
            {
                long long min_val = nums[st.back()];
                st.pop_back();

                int left_bound = st.empty() ? -1 : st.back();

                long long subarray_sum = prefix[i] - prefix[left_bound + 1];

                max_product = max(max_product, min_val*subarray_sum);
            }
            st.push_back(i);
        }
        return max_product % mod;
    }
};