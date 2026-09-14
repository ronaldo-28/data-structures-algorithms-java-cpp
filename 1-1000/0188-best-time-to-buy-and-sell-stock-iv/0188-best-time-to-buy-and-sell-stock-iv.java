class Solution {
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        int[] profits = new int[n];
        for (int i = 1; i <= k ; i++) {
            int max = Integer.MIN_VALUE;
            int profit = 0;
            for( int j = n-1; j >= 0; j--) {
                max = Math.max(max, profits[j]+prices[j]);
                profit = Math.max(profit, max-prices[j]);
                profits[j] = profit;
            }
        }
        return profits[0];
    }
}