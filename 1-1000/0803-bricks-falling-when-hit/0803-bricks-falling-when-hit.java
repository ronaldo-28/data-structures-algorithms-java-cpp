class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int rows = grid.length, cols = grid[0].length;
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]]--;
        }
        for (int c = 0; c < cols; c++) {
            mapBricks(grid, 0, c);
        }
        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            grid[r][c]++;
            if (isStable(grid, r, c)) {
                result[i] = mapBricks(grid, r, c) - 1;
            }
        }
        return result;
    }
    int mapBricks(int[][]grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
            return 0;
        }
        grid[x][y] = 2;
        return 1 + mapBricks(grid, x - 1, y)
            + mapBricks(grid, x + 1, y)
            + mapBricks(grid, x, y - 1)
            + mapBricks(grid, x, y + 1);
    }
    boolean isStable(int[][] grid, int x, int y) {
        return grid[x][y] == 1 && (x == 0 || grid[x - 1][y] == 2 || 
        x < grid.length - 1 && grid[x + 1][y] == 2 ||
        y > 0 && grid[x][y - 1] == 2 ||
        y < grid[0].length - 1 && grid[x][y + 1] == 2
        );
    }
}