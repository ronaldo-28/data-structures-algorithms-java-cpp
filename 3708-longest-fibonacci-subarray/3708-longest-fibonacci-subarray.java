class Solution {
    public int longestSubarray(int[] nums) {
        int max=2;
        int count=2;
        for(int j=2;j<nums.length;j++){
                if(nums[j]==nums[j-1]+nums[j-2]){
                    count++;
                }else{
                    count=2;
                }
           max=Math.max(max, count);
        }
        return max;
    }
}