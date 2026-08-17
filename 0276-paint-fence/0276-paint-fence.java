class Solution {
    public int numWays(int n, int k) {
        
        if(n==0) return 0;
        if(n==1) return k;
        if(n==2) return k*k; // why k*k ? because we can have 2 posts with same color
        
        int[]dp = new int[n+1];
        
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k*k;
        
        for(int i=3;i<n+1;i++) {
            dp[i] = (k-1)*(dp[i-1]+dp[i-2]);
        }
        return dp[n];
    }
}