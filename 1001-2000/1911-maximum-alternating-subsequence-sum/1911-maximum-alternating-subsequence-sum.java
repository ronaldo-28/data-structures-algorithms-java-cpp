class Solution {
    public long maxAlternatingSum(int[] nums) {
        long ans=nums[0];
        for(int i=1;i<nums.length;i++){
            ans+=Math.max((nums[i]-nums[i-1]),0);
        }
        return ans;
    }
}