class Solution {
public:
    long long maximumTripletValue(vector<int>& nums) {
        long long  ans = 0;

        long long  maxLeft = nums[0];
        long long  maxMinus = 0;

        for(int i=1;i<nums.size();i++){
            ans = max(ans,maxMinus*nums[i]);
            
            maxMinus = max(maxMinus,maxLeft - (long long )nums[i]);

            maxLeft = max(maxLeft,(long long)nums[i]);
        }

        return ans;
    }
};