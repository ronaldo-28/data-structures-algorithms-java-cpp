class Solution {
    static boolean check;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int count = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[0].length; j++) {
                if (grid2[i][j] == 1) {
                    check = true;
                    findTheIsland(grid1, grid2, i, j);
                    if(check)   count++;
                }
            }
        }
        return count;
    }

    public void findTheIsland(int[][] grid1, int[][] grid2, int row, int col) {
        int Rlen = grid1.length;
        int Clen = grid1[0].length;

        grid2[row][col] = -1; 

        check &= grid1[row][col] == 1;

        if (row + 1 < Rlen && grid2[row + 1][col] == 1)
            findTheIsland(grid1, grid2, row + 1, col);

        if (row - 1 >= 0 && grid2[row - 1][col] == 1)
            findTheIsland(grid1, grid2, row - 1, col);

        if (col + 1 < Clen && grid2[row][col + 1] == 1)
            findTheIsland(grid1, grid2, row, col + 1);

        if (col - 1 >= 0 && grid2[row][col - 1] == 1)
            findTheIsland(grid1, grid2, row, col - 1);

        return;
    }
}