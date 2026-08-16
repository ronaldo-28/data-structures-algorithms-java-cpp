class Solution {
    // int[] DP; 
    public int findLengthOfLCIS(int[] nums) {
        // DP = new int[nums.length];
        return helper(0, 1, 0, nums);
    }
    private int helper(int i, int curmax, int cur_end, int[] nums){
            // base case: if i = 0, return 1
            if (i == nums.length){
                return curmax;
            }
    
            if(cur_end == i-1 && nums[i] > nums[cur_end]){
                return helper(i+1, curmax+1, i, nums);
            }
            else{
                // return Math.max(helper(i+1, curmax, cur_end, nums), helper(i+1, 1, i, nums));
                return Math.max(curmax, helper(i+1, 1, i, nums));
            }
        }
}