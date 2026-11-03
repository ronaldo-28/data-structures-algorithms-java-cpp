class Solution {
public:
    int sumOfGoodNumbers(vector<int>& nums, int k) {
        int ans=0,n=nums.size();
        for(int i=0;i<n;i++){
            if(i>=k){
                if(i+k<n) {
                    if(nums[i]>nums[i-k] && nums[i]>nums[i+k]) ans+=nums[i];
                }
                else if(nums[i]>nums[i-k]) ans+=nums[i];
            }
            else if(i+k<n){
                if(nums[i]>nums[i+k]) ans+=nums[i];
            }
            else ans+=nums[i];
        }
        return ans;
    }
};