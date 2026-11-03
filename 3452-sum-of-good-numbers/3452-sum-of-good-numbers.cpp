class Solution {
public:
    int sumOfGoodNumbers(vector<int>& nums, int k) {
        int n=nums.size();
        int sum=0;
        for(int i=0;i<n;i++){
            if((i - k < 0 || nums[i-k] < nums[i]) && 
                (i+k >= n ||nums[i+k] < nums[i])){
                sum+=nums[i];
            }
        }
        return sum;
    }
};