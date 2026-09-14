import java.util.*;

class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        //dp[i] stores the max profit rechable at point i
        // use long to prevent overflow
        long[] dp = new long[n+1];

        //grouping rides by their end point for 0(1) access during DP
        List<int[]>[] ridesAtEnd = new ArrayList[n+1];
        for (int[] ride : rides){
            int end = ride[1];
            if (ridesAtEnd[end] == null){
                ridesAtEnd[end] = new ArrayList<>();
            }
            ridesAtEnd[end].add(ride);
        }
        for (int i=1; i<=n; i++){
            //default: profit is at least what we had at the previous point
            dp[i] = dp[i-1];

            //if there are rides ending at this point, try picking them up
            if (ridesAtEnd[i] != null){
                for (int[] ride : ridesAtEnd[i]){
                    int start = ride[0];
                    int tip = ride[2];
                    long currentRideProfit = (i-start)+tip;

                    //max profit = profit at start point + profit at this ride
                    dp[i] = Math.max(dp[i], dp[start] + currentRideProfit);
                }
            }
        }
        return dp[n];
    }
}