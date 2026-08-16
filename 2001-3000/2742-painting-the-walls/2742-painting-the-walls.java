public class Solution {
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        // dp[j] = min cost to paint j walls. 
        // Initialize with a large value.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int c = cost[i];
            int t = time[i];
            // Iterate backwards to avoid using the same item multiple times (0/1 Knapsack)
            for (int j = n; j >= 1; j--) {
                // If the paid paiter is busy for t time, the free painter can paint t walls 
                // as each wall takes 1 time. Total 1+t walls get painted and cost is c.
                
                // dp[j] denotes min cost for painting j walls. 
                // so we check for each ith wall to either get it painted by paid painter or not.
                // if we select paid painter the cost would be dp[j-(1+t)] + c . Compare this with existing cost
                // to select min. the 0 check is needed to make sure it does not go negative. 
                dp[j] = Math.min(dp[j], dp[Math.max(0, j - (1 + t))] + c);
            }
        }
        return dp[n];
    }
}