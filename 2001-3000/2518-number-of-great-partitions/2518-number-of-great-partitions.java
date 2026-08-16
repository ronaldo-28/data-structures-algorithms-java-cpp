class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length, MOD = 1_000_000_007;

        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // base check
        if (totalSum < (2 * k)) {
            return 0;
        }

        int[] dp = new int[k];
        dp[0] = 1;

        for (int num : nums) {
            for (int i = k - 1; i >= num; i--) {
                dp[i] = (dp[i] + dp[i - num]) % MOD;
            }    
        }
        
        long totalPartitions = 1;
        for (int i = 0; i < n; i++) {
            totalPartitions = (totalPartitions * 2) % MOD;
        }

        long totalBadWays = 0;
        for (int i = 0; i < k; i++) {
            totalBadWays = (totalBadWays + dp[i]) % MOD;
        }

        long totalBadPartitions = (totalBadWays * 2) % MOD;

        long greatPartitions = (totalPartitions - totalBadPartitions + MOD) % MOD;

        return (int) greatPartitions;
    }
}