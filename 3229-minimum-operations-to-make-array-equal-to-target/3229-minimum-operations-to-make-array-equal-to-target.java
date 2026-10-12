class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = target.length, valley = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            int diff = target[i] - nums[i];
            sum += Math.max(diff - valley, 0);
            valley = diff;
        }

        return sum + Math.max(-valley, 0);
    }
}