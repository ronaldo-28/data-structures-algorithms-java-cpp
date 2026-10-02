class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int[] onesInRow = new int[grid.length];
        int[] onesInCol = new int[grid[0].length];

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                onesInRow[r] += grid[r][c];
                onesInCol[c] += grid[r][c];
            }
        }
        long count = 0L;
        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                if (grid[r][c] == 1) {
                    count += (long) (onesInRow[r] - 1) * (onesInCol[c] - 1);
                }
            }
        }

        return count;
    }
}