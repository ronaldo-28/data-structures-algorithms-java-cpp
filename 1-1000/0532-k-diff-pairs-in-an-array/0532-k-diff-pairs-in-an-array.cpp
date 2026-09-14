class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        if (k < 0) return 0; // absolute difference can't be negative
        
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int l = 0, r = 1;
        int count = 0;
        
        while (r < n && l < n) {
            if (l == r || nums[r] - nums[l] < k) {
                r++;
            } else if (nums[r] - nums[l] > k) {
                l++;
            } else {
                count++;
                l++;
                r++;
               
                while (r < n && nums[r] == nums[r - 1]) r++;
            }
        }
        
        return count;
    }
};