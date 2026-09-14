class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfWays(String s, String t, long k) {
        int n = s.length();
        
        // Step 1: Optimize match counting to O(n) avoiding the O(n^2) indexOf loop trap
        String doubleStr = s + s;
        
        // If t isn't even a cyclic shift of s, it's impossible to reach
        if (doubleStr.substring(0, doubleStr.length() - 1).indexOf(t) == -1) {
            return 0;
        }
        
        // Find the smallest shift period of s
        int period = doubleStr.indexOf(s, 1);
        int matchCount = n / period;
        
        // Step 2: Matrix Exponentiation for the DP transition
        long[][] baseMatrix = {
            {0, n - 1},
            {1, n - 2}
        };
        
        long[][] finalMatrix = matrixPower(baseMatrix, k);
        long dp0 = finalMatrix[0][0]; 
        long dp1 = finalMatrix[1][0]; 
        
        // Step 3: Combine results
        if (s.equals(t)) {
            return (int) ((dp0 + (matchCount - 1) * dp1) % MOD);
        } else {
            return (int) ((matchCount * dp1) % MOD);
        }
    }

    // Helper functions for Matrix Exponentiation O(log k)
    private long[][] matrixPower(long[][] matrix, long power) {
        long[][] result = {{1, 0}, {0, 1}};
        long[][] base = matrix;
        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiplyMatrices(result, base);
            }
            base = multiplyMatrices(base, base);
            power >>= 1;
        }
        return result;
    }

    private long[][] multiplyMatrices(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int l = 0; l < 2; l++) {
                    C[i][j] = (C[i][j] + A[i][l] * B[l][j]) % MOD;
                }
                if (C[i][j] < 0) C[i][j] += MOD;
            }
        }
        return C;
    }
}