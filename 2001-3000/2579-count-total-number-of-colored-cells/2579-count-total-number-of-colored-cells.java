class Solution {
    // Method to calculate the number of colored cells in an n x n grid
    // following a specific pattern.
    public long coloredCells(int n) {
        // The formula to calculate the colored cells is based on the observation
        // that there are 2 * n * (n - 1) cells that are colored in rows and columns,
        // plus 1 for the initial cell (the center cell in case of an odd-sized grid,
        // or one of the centers in an even-sized grid).
      
        // 2 * n * (n - 1) accounts for the rows and columns colored cells
        // +1 represents the initial cell that gets colored
        return 2L * n * (n - 1) + 1;
    }
}