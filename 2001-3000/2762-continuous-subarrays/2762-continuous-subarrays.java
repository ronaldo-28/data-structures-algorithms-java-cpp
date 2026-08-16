class Solution {
    public long continuousSubarrays(int[] nums) {
        int start=0;
        int end=0;
        int curMin = Integer.MAX_VALUE;
        int curMax = Integer.MIN_VALUE;
        long count=0;
        int n = nums.length;
        while(end < n){
            curMin = Math.min(curMin, nums[end]);
            curMax = Math.max(curMax, nums[end]);
            if(curMax - curMin >2){
                start = end;
                curMin = nums[end];
                curMax = nums[end];
                while(start-1>=0 && Math.abs(nums[start-1] - nums[end])<=2){
                    start--;
                    curMin = Math.min(curMin, nums[start]);
                    curMax = Math.max(curMax, nums[start]);
                }    
            }
            count += (end - start + 1);
            end++;
        }
        return count;
    }
}