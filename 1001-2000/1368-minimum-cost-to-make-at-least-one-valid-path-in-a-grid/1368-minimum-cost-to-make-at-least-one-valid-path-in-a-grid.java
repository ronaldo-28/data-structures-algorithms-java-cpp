class Solution {
    int[] dx = new int[]{0,0,1,-1};
    int[] dy = new int[]{1,-1,0,0};
    public int minCost(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();

        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        int cost = 0;

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        dfs(dp,grid,0,0,q,cost);
        if(dp[n-1][m-1] == 0) return 0;
        while(!q.isEmpty()){
            cost++;
            int size = q.size();
            while(size > 0){
                int[] top = q.poll();
                for(int i=0;i<4;i++){
                    dfs(dp,grid,top[0]+dx[i],top[1]+dy[i],q,cost);
                }
                size--;
            }
        }
        return dp[n-1][m-1];
    }

    private void dfs(int[][] dp, int[][] grid, int r, int c, Queue<int[]> q, int cost){
        int n = grid.length;
        int m = grid[0].length;

        if(r < 0 || r >= n || c < 0 || c >= m || dp[r][c] != Integer.MAX_VALUE){
            return;
        } 

        dp[r][c] = cost;

        q.offer(new int[]{r,c});

        int dir = grid[r][c] - 1;

        dfs(dp,grid,r + dx[dir],c+dy[dir],q,cost);
    }
}