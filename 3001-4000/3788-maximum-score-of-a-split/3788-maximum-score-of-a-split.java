class Solution {
    public long maximumScore(int[] nums) {
        long ans = nums[0] - nums[1], prefix_sum = 0;
        int n = nums.length, min = nums[n - 1];
        for(int i = 0; i < n - 1; i++) prefix_sum += nums[i];
        for(int i = n - 2; i >= 0; i--) {
            ans = Math.max(ans, prefix_sum - min);
            min = Math.min(min, nums[i]);
            prefix_sum -= nums[i];
        }
        return ans;
    }
}