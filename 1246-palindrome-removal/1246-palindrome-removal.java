class Solution {
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int size = 1; size <= n; size++) {
            for (int start = 0; start + size <= n; start++) {
                int end = start + size - 1;
                if (size == 1) {
                    dp[start][end] = 1;
                } else {
                    int min = n;
                    boolean debug = size == n;
                    if (arr[start] == arr[end]) {
                        min = Math.min (min, (end - start > 1 ? dp[start + 1][end - 1] : 1));
                    } 
                    for (int k = start; k < end; k++) {
                        min = Math.min (min, dp[start][k] + dp[k + 1][end]);
                    }
                    dp[start][end] = min;
                }
            }
        }
        return dp[0][n - 1];
    }
}