class Solution {
    boolean exist(int[][] grid, int[][] rowSum, int[][] colSum, int size) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        int rowMax = rowSize - size;
        int colMax = colSize - size;

        for ( int row = 0; row <= rowMax; row++ ) {
            for ( int col = 0; col <= colMax; col++ ) {
                int sum = rowSum[row][col+size] - rowSum[row][col];
// System.out.printf("size=%d, row=%d, col=%d, sum=%d\n", size, row, col, sum);
                boolean match = true;
                for ( int ii = 0; match && ii < size; ii++ ) {
                    int sum1 = (rowSum[row+ii][col+size] - rowSum[row+ii][col]);
                    int sum2 = (colSum[row+size][col+ii] - colSum[row][col+ii]);
// System.out.printf("size=%d, row=%d, col=%d, ii=%d, sum1=%d, sum2=%d\n", size, row, col, ii, sum1, sum2);
                    match = sum1 == sum && sum2 == sum;
                }
                
                if ( match ) {
                    int sum1 = 0, sum2 = 0;
                    for ( int ii = 0; ii < size; ii++ ) {
                        sum1 += grid[row+ii][col+ii];
                        sum2 += grid[row+ii][col+size-1-ii];
                    }
// System.out.printf("sum1=%d, sum2=%d\n", sum1, sum2);
                    if ( sum1 == sum && sum2 == sum ) return true;
                }
            }
        }
        return false;
    }
    public int largestMagicSquare(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        int[][] rowSum = new int[rowSize][colSize+1];
        int[][] colSum = new int[rowSize+1][colSize];

        for ( int row = 0; row < rowSize; row++ ) {
            for ( int col = 0; col < colSize; col++ ) {
                rowSum[row][col+1] = rowSum[row][col] + grid[row][col];
                colSum[row+1][col] = colSum[row][col] + grid[row][col];
            }
        }

        for ( int size = Math.min(rowSize, colSize); size > 1; size-- ) {
            if ( exist(grid, rowSum, colSum, size) ) return size;
        }
        return 1;
    }
}