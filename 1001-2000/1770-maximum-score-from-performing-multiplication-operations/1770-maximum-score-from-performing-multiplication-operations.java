class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;

        int[] dp = new int[m + 1];

        for (int op = m - 1; op >= 0; op--) {
            for (int left = 0; left <= op; left++) {
                dp[left] = Math.max(
                    nums[left] * multipliers[op] + dp[left + 1],
                    nums[n - 1 - (op - left)] * multipliers[op] + dp[left]
                );
            }
        }

        return dp[0];
    }
}