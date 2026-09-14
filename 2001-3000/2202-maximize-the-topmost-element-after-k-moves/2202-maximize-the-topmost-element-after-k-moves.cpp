#include <vector>
#include <algorithm>

class Solution {
public:
    int maximumTop(std::vector<int>& nums, int k) {
        int n = nums.size();
        
        // Edge case: Single element and odd k
        if (n == 1 && k % 2 != 0) return -1;
        
        // If k > n, we can always pick the global maximum
        if (k > n) {
            int res = -1;
            for(int x : nums) res = std::max(res, x);
            return res;
        }
        
        int max_val = -1;
        // Check max of first k-1 elements
        for (int i = 0; i < k - 1; ++i) {
            max_val = std::max(max_val, nums[i]);
        }
        
        // If k < n, we can also just leave the element at index k on top
        if (k < n) {
            max_val = std::max(max_val, nums[k]);
        }
        
        return max_val;
    }
};