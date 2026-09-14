class Solution {
    public long validSubarrays(int[] nums, int k) {
        int n = nums.length;
        int prev1 = -1, prev2 = -1; //prev1 is the previous peak, prev2 is the peak before that
        long ans = 0;
        for(int i = 1; i < n - 1; i++) {
            if(nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) { //when the current index is a peak
                ans += Math.min(prev1 - prev2, k + 1L) * Math.min(i - prev1, k + 1L);
                prev2 = prev1;
                prev1 = i;
            }
        }
        //we must calculate for the final peak, cuz we haven't processed it yet
        return ans + Math.min(prev1 - prev2, k + 1L) * Math.min(n - prev1, k + 1L);
    }
}