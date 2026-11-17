class Solution {
    public int minXor(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];

        // Base case: when we have only one partition
        int currentXor = 0;
        for (int j = 0; j < n; j++) {
            currentXor ^= nums[j];
            dp[j] = currentXor;
        }

        // Compute for remaining k - 1 partitions
        for (int partition = 1; partition < k; partition++) {
            // We traverse in reverse to avoid overwriting data we still need
            for (int end = n - 1; end >= partition; end--) {
                int tempXor = 0;
                int minMaxXor = Integer.MAX_VALUE;

                // Try all possible cut points for the last segment
                for (int start = end; start >= partition; start--) {
                    tempXor ^= nums[start];
                    minMaxXor = Math.min(minMaxXor, Math.max(dp[start - 1], tempXor));
                }

                dp[end] = minMaxXor;
            }
        }

        return dp[n - 1];
    }
}