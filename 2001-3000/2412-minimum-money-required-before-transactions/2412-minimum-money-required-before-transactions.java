class Solution {
    public long minimumMoney(int[][] transactions) {
        long totalLoss = 0;
        int maxAdditionalCost = 0;

        // One single pass through the array to collect parameters in O(N)
        for (int[] t : transactions) {
            int cost = t[0];
            int cashback = t[1];

            if (cost > cashback) {
                // If it's a losing transaction, it contributes to the absolute base loss
                totalLoss += (cost - cashback);
                
                // If this losing transaction is placed last among the losing group, 
                // we only experience the loss of OTHER transactions before paying this cost.
                // Remaining cost required to process this = cost - (cost - cashback) = cashback.
                if (cashback > maxAdditionalCost) {
                    maxAdditionalCost = cashback;
                }
            } else {
                // If it's a profitable or neutral transaction (cost <= cashback), 
                // it is not included in totalLoss. If placed first among profitable ones, 
                // we need its full initial cost.
                if (cost > maxAdditionalCost) {
                    maxAdditionalCost = cost;
                }
            }
        }

        // The worst-case required amount is the total baseline loss plus the worst bottleneck peak
        return totalLoss + maxAdditionalCost;
    }
}