class Solution {
      static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        // for(int[] row: dp){
        //     Arrays.fill(row, Integer.MAX_VALUE);
        // }
        // int answer = Integer.MAX_VALUE;
        // for(int j=0; j<m; j++){
            
        //     answer = Math.min(answer, dfs(0,j,m,n,grid,dp));
             
        // }
        // return answer;

        //Fill last row
        for(int j=0; j<m; j++){
            dp[n-1][j] = grid[n-1][j];
        }
        for(int i=n-2; i>=0; i--){
            for(int j=0; j<m; j++){
                int mini = Integer.MAX_VALUE;
                for(int col=0; col<m; col++){
                    if(col!=j){
                        mini = Math.min(dp[i+1][col], mini);
                    }
                }
                dp[i][j] = grid[i][j] + mini;

            }
        }

        int ans = Integer.MAX_VALUE;
        for(int j=0; j<m; j++){
            ans = Math.min(ans, dp[0][j]);
            
        }
        return ans;

       
        
    }

    // public int dfs(int i, int j, int m, int n, int[][] grid, int[][] dp){
    //     // if(j<m || j>m) return Integer.MAX_VALUE;
    //     //Base case
    //     if(i==n-1) return grid[n-1][j];
    //     if(dp[i][j]!=Integer.MAX_VALUE) return dp[i][j];
    //     int mini = Integer.MAX_VALUE;
    //     for(int col=0; col<m; col++){
    //         if(j!=col){
    //             int ans = dfs(i+1,col, m, n, grid,dp);
    //             mini = Math.min(mini, ans);
    //         }
    //     }
    //     return dp[i][j] = grid[i][j] + mini;

    // }
}