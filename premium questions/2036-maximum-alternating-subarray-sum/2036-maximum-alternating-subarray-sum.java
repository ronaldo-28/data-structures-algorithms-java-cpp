class Solution {
    
    public long maximumAlternatingSubarraySum(int[] nums) {
        long max = nums[0];
        long a = 0, b = nums[0], temp;
        for(int i = 1; i < nums.length; i++){
            temp = Math.max(nums[i], a + nums[i]);
            a = b - nums[i];
            b = temp;
            max = Math.max(max, Math.max(a,b));
        }
        return max;
    }
}