class Solution {
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        
        long[] dp = new long[n]; 

        //Initialize first row
        for(int a=0; a<n ; a++){
            dp[a] = points[0][a];
        }      

        //Iterate through each row

        for(int row = 1; row < m; row++){
            // Left sweep
            for(int b=1; b<n; b++){
                dp[b] = Math.max(dp[b], dp[b-1]-1);
            }

            // right sweep and directly update dp
            long right= dp[n-1];
            dp[n-1] += points[row][n-1];
            for(int c= n-2; c>=0; c--){
                right = Math.max(right-1, dp[c]);
                dp[c]= right + points[row][c];
            }
        }

        long maximum = 0;
        for(long score: dp){
            maximum= Math.max(maximum, score);
        }

        return maximum;
    }
}