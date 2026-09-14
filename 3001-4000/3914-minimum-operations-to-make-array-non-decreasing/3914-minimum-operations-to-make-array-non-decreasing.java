class Solution {
    public long minOperations(int[] nums) {
        long r = 0;
        for (int i = 1; i < nums.length; i++) {
            r += Math.max(nums[i - 1] - nums[i], 0);
        }
        return r;
    }
}