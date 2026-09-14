class Solution {
public:
    int maxAbsoluteSum(vector<int>& nums) {
        int bestEndingWithMaxSum=nums[0];
        int maxsum=nums[0];

        int bestEndingWithMinSum=nums[0];
        int minsum=nums[0];

        for(int i=1;i<nums.size();i++){
            bestEndingWithMaxSum=max(bestEndingWithMaxSum+nums[i],nums[i]);
            maxsum=max(bestEndingWithMaxSum,maxsum);
            
            bestEndingWithMinSum=min(bestEndingWithMinSum+nums[i],nums[i]);
            minsum=min(bestEndingWithMinSum,minsum);

        }
        return max(abs(maxsum),abs(minsum));
    }
};