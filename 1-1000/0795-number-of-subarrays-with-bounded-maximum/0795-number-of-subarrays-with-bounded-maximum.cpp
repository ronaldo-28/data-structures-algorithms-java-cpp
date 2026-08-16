class Solution {
public:
    int numSubarrayBoundedMax(vector<int>& nums, int mn, int mx) {
        int ans = 0;
        int left = 0;
        int n = nums.size();

        for(int right = 0;right < n;right++){
            if(nums[right] > mx){
                left  = right+1;
                continue;
            }

            ans += (right - left + 1);
        }

        left = 0;
        for(int right = 0;right<n;right++){
            if(nums[right] >= mn){
                left  = right+1;
                continue;
            }
            ans -= (right - left + 1);
        }
        return ans;
    }
};