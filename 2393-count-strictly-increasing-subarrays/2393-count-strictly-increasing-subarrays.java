class Solution {
    public long countSubarrays(int[] nums) {
        long res = 0;
        int streak = 0;
        int prev = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > prev) {
                streak += 1;
            } else {
                streak = 1;
            }
            res += streak;
            prev = nums[i];
        }
        return res;
    }
}