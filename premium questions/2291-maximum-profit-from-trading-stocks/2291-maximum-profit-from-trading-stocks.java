class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        // dp[j] will store the maximum profit achievable with budget j
        int[] dp = new int[budget + 1];
        
        int n = present.length;
        
        // Iterate through each stock
        for (int i = 0; i < n; i++) {
            int cost = present[i];
            int profit = future[i] - present[i];
            
            // Only consider stocks that yield a positive profit
            if (profit > 0) {
                // Traverse backwards to avoid using the same stock multiple times
                for (int j = budget; j >= cost; j--) {
                    dp[j] = Math.max(dp[j], dp[j - cost] + profit);
                }
            }
        }
        
        // The maximum profit with the full budget
        return dp[budget];
    }
}