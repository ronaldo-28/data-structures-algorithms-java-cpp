class Solution {
    public int countServers(int[][] grid) {
        int r=grid.length;
        int c=grid[0].length;
        int servers = 0;
        int[] rowCount = new int[r];
        int[] colCount = new int[c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j]==1){
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(grid[i][j]==1 && (rowCount[i]>1 || colCount[j]>1)) servers++;
            }
        }
        return servers;
    }
}