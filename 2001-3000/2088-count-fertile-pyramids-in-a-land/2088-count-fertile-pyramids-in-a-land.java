class Solution {
    public int countPyramids(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[i][j]=grid[i][j];
            }
        }
        int count =0;
        for(int i=m-2;i>=0;i--){
            for(int j=1;j<n-1;j++){
                if(grid[i][j]==0) continue;
                else{
                    grid[i][j]+=Math.min(grid[i+1][j],Math.min(grid[i+1][j+1],grid[i+1][j-1]));
                }count+=grid[i][j]-1;

            }
        } 
        for(int i=1;i<m;i++){
            for(int j=n-2;j>0;j--){
                if(dp[i][j]==0) continue;
                else{
                    dp[i][j]+=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i-1][j+1]));
                }
               count+=dp[i][j]-1;

            }
        } 
        return count;

    }
}