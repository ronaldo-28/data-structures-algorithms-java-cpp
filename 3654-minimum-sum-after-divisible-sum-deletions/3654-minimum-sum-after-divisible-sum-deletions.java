class Solution {
    public long minArraySum(int[] nums, int k) {
        long pref_sum = 0L;
        
        long[] dp = new long[k];
        java.util.Arrays.fill(dp, Long.MAX_VALUE); // Represents infinity
        dp[0] = 0; // Base case for remainder 0
    
        for (int num : nums) {
            pref_sum += num; // Add current number to sum
            int rem = (int)(pref_sum % k); // Calculate remainder
    
            pref_sum = Math.min(pref_sum, dp[rem]); // Update current sum
                
            dp[rem] = Math.min(pref_sum, dp[rem]); // Update dp table
        }
            
        return pref_sum;
    }
}