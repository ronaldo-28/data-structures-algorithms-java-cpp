class Solution {
    int[][] grid;
    int n, m;
    int[][] dp;
    public int calculateMinimumHP(int[][] dungeon) {
        grid = dungeon;
        n = grid.length;
        m = grid[0].length;
        dp = new int[n][m];
        for(int[] i : dp) Arrays.fill(i, -1);
        return dfs(0, 0);
    }
    private int dfs(int i, int j){
        if(i >= n || j >= m){
            return Integer.MAX_VALUE;
        }
        if(i == n - 1 && j == m - 1){
            return Math.max(1, 1 - grid[i][j]);
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int down = dfs(i + 1, j);
        int right = dfs(i, j + 1);
        int health = Math.min(down, right) - grid[i][j];
        return dp[i][j] = Math.max(health, 1);        
    }
}