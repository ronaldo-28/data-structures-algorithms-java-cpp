class Solution {
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        long[] last = new long[n + 1], preSum = new long[n + 1];
        last[0] = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] deque = new int[n + 1];
        int beg = 0, end = 0;
        // dp[i] = dp[j] + 1, if last[j] + s[j] <= s[i]
        // if j < k and last[k] + s[k] <= last[j] + s[j],
        // cause f[j] <= f[k], so drop j
        for (int i = 1; i <= n; i++) {
            while (beg < end && last[deque[beg + 1]] + preSum[deque[beg + 1]] <= preSum[i]) {
                beg++;
            }
            dp[i] = dp[deque[beg]] + 1;
            last[i] = preSum[i] - preSum[deque[beg]];
            long cur = last[i] + preSum[i];
            while (beg < end && cur <= preSum[deque[end]] + last[deque[end]]) {
                end--;
            }
            deque[++end] = i;
        }
        return dp[n];
    }

}