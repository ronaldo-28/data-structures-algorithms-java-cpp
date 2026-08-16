class Solution {
   
        private int rows; 
    private int cols; 
    private int island = 0; 

    public int numIslands(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            search(grid, row); 
        }

        return island; 
    }

    public void search(char[][] grid, int row) {
        char[] finalrow = grid[row];

        for (int col = 0; col < cols; ++col) {
            if (finalrow[col] == '1') { 
                mark(grid, row, col); 
                island++; 
            }
        }
    }

    public void mark(char[][] grid, int row, int col) {
        grid[row][col] = '*'; 

        if (row > 0 && grid[row - 1][col] == '1')
            mark(grid, row - 1, col);
        if (row + 1 < rows && grid[row + 1][col] == '1')
            mark(grid, row + 1, col);
        if (col > 0 && grid[row][col - 1] == '1')
            mark(grid, row, col - 1);
        if (col + 1 < cols && grid[row][col + 1] == '1')
            mark(grid, row, col + 1);
    }
}
    