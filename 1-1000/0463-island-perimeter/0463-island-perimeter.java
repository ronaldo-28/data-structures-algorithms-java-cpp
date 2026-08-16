class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i=0;i<row;i+=1){
            for(int j=0;j<col;j+=1){
                if(grid[i][j] == 1){
                    perimeter+=4;

                    if(i<row-1 && grid[i+1][j]==1){
                        perimeter-=2;
                    }
                    if(j<col-1 && grid[i][j+1]==1){
                        perimeter-=2;
                    }
                }
            }
        }
        return perimeter;
    }
}