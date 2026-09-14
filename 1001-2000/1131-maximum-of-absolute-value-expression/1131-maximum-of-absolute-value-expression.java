class Solution {
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int[][] dp = new int[4][m];
        for(int i = 0;i < m;i++) {
            int x = arr1[i] + arr2[i] + i, y  = arr1[i] - arr2[i] + i, z =  -arr1[i] + arr2[i] + i, k = i - arr1[i] - arr2[i];
            dp[0][i] = x;
            dp[1][i] = y;
            dp[2][i] = z;
            dp[3][i] = k;
        }
        int maxAns = Integer.MIN_VALUE;
        for(int i = 0;i < 4;i++) {
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for(int x : dp[i]) {
                if(x > max) {
                    max = x;
                }
                if(x < min) {
                    min = x;
                }
            }
            maxAns = Math.max(maxAns, max - min);
        }
        return maxAns;
    }
}