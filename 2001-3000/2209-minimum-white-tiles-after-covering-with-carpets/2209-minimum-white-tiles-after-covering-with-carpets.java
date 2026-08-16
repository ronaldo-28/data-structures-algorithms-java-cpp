class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        if(numCarpets * carpetLen >= n) return 0;
        int count = 0;
        int[][] dp = new int[numCarpets + 1][n + 1];
        char[] arr = floor.toCharArray();
        for(int i = 0; i < n; i++) dp[0][i + 1] = dp[0][i] + arr[i] - '0';
        for(int i = 1; i <= numCarpets; i++) {
            for(int j = carpetLen; j <= n; j++) dp[i][j] = Math.min(dp[i][j - 1] + arr[j - 1] - '0', dp[i - 1][j - carpetLen]);
        }
        return dp[numCarpets][n];
    }
}