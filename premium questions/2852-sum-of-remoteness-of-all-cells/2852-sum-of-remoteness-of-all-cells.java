class Solution {
    public long sumRemoteness(int[][] grid) {
       int n = grid.length;
       long sum = 0;
       for(int i=0;i<n;i++) {
        for(int j=0;j<n;j++) {
            if(grid[i][j]>0)
                sum += grid[i][j];
        }
       }
       long ans=0;
       for(int i=0;i<n;i++) {
        for(int j=0;j<n;j++) {
            if(grid[i][j]>0) {
                long res[] = new long[2];
                dfs(grid,res,i,j,n);
                ans += (long) (sum-res[0])*res[1];
            }
        }
       }
       return ans;
    }

    public void dfs(int[][] grid,long[] res,int i,int j,int n) {
        if(i<0 || i>=n || j<0 || j>=n || grid[i][j]<=0)
            return;
        res[0] += grid[i][j];
        res[1]++;
        grid[i][j]=-1;
        dfs(grid,res,i+1,j,n);
        dfs(grid,res,i,j+1,n);
        dfs(grid,res,i-1,j,n);
        dfs(grid,res,i,j-1,n);
    }
}