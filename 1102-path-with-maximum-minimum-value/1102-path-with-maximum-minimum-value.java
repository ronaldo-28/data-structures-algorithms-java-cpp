class Solution {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] grid) {
        int low = 0, high = Math.min(grid[0][0], grid[grid.length - 1][grid[0].length - 1]);

        int ans = high;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (isPossible(grid, 0, 0, mid, new boolean[grid.length][grid[0].length])) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean isPossible(int[][] grid, int row, int col, int mid, boolean[][] visited) {
        int n = grid.length, m = grid[0].length;
        if (row == n - 1 && col == m - 1) return true;

        visited[row][col] = true;

        for (int[] dir : DIRS) {
            int nr = row + dir[0], nc = col + dir[1];

            if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && grid[nr][nc] >= mid) {
                if (isPossible(grid, nr, nc, mid, visited)) return true;
            }
        }
        return false;
    }
}