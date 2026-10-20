class Solution {
public:
    int maxGoodNumber(vector<int>& nums) {
        int ans = INT_MIN;
        for(int i=0; i<6; i++){
            int cur = nums[2];
            cur += nums[1] << (32 - __builtin_clz(cur));
            cur += nums[0] << (32 - __builtin_clz(cur));
            if(cur > ans) ans = cur;
            next_permutation(nums.begin(), nums.end());
        }
        return ans;
    }
};