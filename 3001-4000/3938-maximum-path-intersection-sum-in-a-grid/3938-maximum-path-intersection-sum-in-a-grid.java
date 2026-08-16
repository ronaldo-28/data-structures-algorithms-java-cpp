class Solution {
    public int maxScore(int[][] grid) {
        int ans = Math.max(calculateEdgeRow(grid, 0), calculateEdgeRow(grid, grid.length-1));
        ans = Math.max(ans, calculateEdgeCol(grid, 0));
        ans = Math.max(ans, calculateEdgeCol(grid, grid[0].length-1));

        for (int i = 1; i < grid.length-1; i++) {
            int maxRow = grid[i][0];
            for (int j = 1; j < grid[0].length-1; j++) {
                maxRow = Math.max(maxRow + grid[i][j], grid[i][j]);
                ans = Math.max(ans, maxRow);
            }
            ans = Math.max(ans, maxRow + grid[i][grid[0].length-1]);
        }
        for (int i = 1; i < grid[0].length-1; i++) {
            int maxCol = grid[0][i];
            for (int j = 1; j < grid.length-1; j++) {
                maxCol = Math.max(maxCol + grid[j][i], grid[j][i]);
                ans = Math.max(ans, maxCol);
            }
            ans = Math.max(ans, maxCol + grid[grid.length-1][i]);
        }
        return ans;
    }

    private int calculateEdgeRow(int[][] grid, int row) {
        int ans = Integer.MIN_VALUE;
        int sum = grid[row][0];
        for (int j = 1; j < grid[0].length; j++) {
            sum = Math.max(grid[row][j-1]+grid[row][j], sum+grid[row][j]);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    private int calculateEdgeCol(int[][] grid, int col) {
        int ans = Integer.MIN_VALUE;
        int sum = grid[0][col];
        for (int j = 1; j < grid.length; j++) {
            sum = Math.max(grid[j-1][col]+grid[j][col], sum+grid[j][col]);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}