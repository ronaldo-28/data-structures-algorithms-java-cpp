public class Solution {
    private static final int MOD = 1000000007;
    
    public int dieSimulator(int n, int[] rollMax) {
        // Use 2D arrays instead of 3D to reduce memory access overhead
        // dp[j][k] = number of ways ending with face j appearing k+1 consecutive times
        int[][] dp = new int[6][16];
        int[][] newDp = new int[6][16];
        
        // Base case: first roll
        for (int j = 0; j < 6; j++) {
            dp[j][0] = 1;
        }
        
        // Precompute sums for optimization
        int[] faceSum = new int[6];
        
        for (int i = 2; i <= n; i++) {
            // Calculate sum for each face to avoid repeated calculations
            for (int j = 0; j < 6; j++) {
                faceSum[j] = 0;
                for (int k = 0; k < rollMax[j]; k++) {
                    faceSum[j] = (faceSum[j] + dp[j][k]) % MOD;
                }
            }
            
            int totalSum = 0;
            for (int j = 0; j < 6; j++) {
                totalSum = (totalSum + faceSum[j]) % MOD;
            }
            
            // Clear newDp array efficiently
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < rollMax[j]; k++) {
                    newDp[j][k] = 0;
                }
            }
            
            for (int j = 0; j < 6; j++) {
                // k = 0: starting new sequence (total - current face sum)
                newDp[j][0] = (totalSum - faceSum[j] + MOD) % MOD;
                
                // k > 0: continuing sequence
                for (int k = 1; k < rollMax[j]; k++) {
                    newDp[j][k] = dp[j][k-1];
                }
            }
            
            // Swap arrays (more efficient than copying)
            int[][] temp = dp;
            dp = newDp;
            newDp = temp;
        }
        
        // Calculate final result
        int result = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 0; k < rollMax[j]; k++) {
                result = (result + dp[j][k]) % MOD;
            }
        }
        
        return result;
    }
}