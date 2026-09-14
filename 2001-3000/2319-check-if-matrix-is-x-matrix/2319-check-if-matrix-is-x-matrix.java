class Solution {
    public boolean checkXMatrix(int[][] grid) {
        return isX(grid, 0, grid.length - 1, 0, grid[0].length - 1);
    }

    private boolean isX(int[][] grid, int iStart, int iFinish, int jStart, int jFinish) {
        if (grid[iStart][jStart] == 0
                || grid[iStart][jFinish] == 0
                || grid[iFinish][jStart] == 0
                || grid[iFinish][jFinish] == 0) {
            return false;
        }

        for (int i = iStart + 1; i <= iFinish - 1; i++) {
            if (grid[i][jStart] != 0 || grid[i][jFinish] != 0) {
                return false;
            }
        }

        for (int j = jStart + 1; j <= jFinish - 1; j++) {
            if (grid[iStart][j] != 0 || grid[iFinish][j] != 0) {
                return false;
            }
        }

        if (iFinish - iStart <= 1 || jFinish - jStart <= 1) {
            return true;
        }

        return isX(grid, ++iStart, --iFinish, ++jStart, --jFinish);
    }
}