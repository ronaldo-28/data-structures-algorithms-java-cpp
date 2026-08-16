class Solution {
    static {
        int[][] testMatrix = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        for (int i = 0; i < 500; i++) {
            int x = new Solution().longestIncreasingPath(testMatrix);
        }
    }
    int m;
    int n;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxPath = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                maxPath = Math.max(maxPath, dfs(matrix, dp, i, j));
            }
        }
        return maxPath;
    }
    private int dfs(int[][] matrix, int[][] dp, int i, int j){
        if(dp[i][j] != 0) return dp[i][j];
        int currVal = matrix[i][j];
        int maxLen = 1;
        if(i > 0 && matrix[i - 1][j] > currVal){
            maxLen = Math.max(maxLen, 1 + dfs(matrix, dp, i - 1, j));
        }
        if(i < m - 1 && matrix[i + 1][j] > currVal){
            maxLen = Math.max(maxLen, 1 + dfs(matrix, dp, i + 1, j));
        }
        if(j > 0 && matrix[i][j - 1] > currVal){
            maxLen = Math.max(maxLen, 1 + dfs(matrix, dp, i, j - 1));
        }
        if(j < n - 1 && matrix[i][j + 1] > currVal){
            maxLen = Math.max(maxLen, 1 + dfs(matrix, dp, i, j + 1));
        }
        dp[i][j] = maxLen;
        return maxLen;
    }
}