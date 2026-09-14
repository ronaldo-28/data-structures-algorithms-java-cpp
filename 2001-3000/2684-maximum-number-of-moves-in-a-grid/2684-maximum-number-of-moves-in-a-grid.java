class Solution {
    int max = 0;

    public int maxMoves(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            dfs(grid, i, 0);
            if (max == grid[0].length - 1)
                break;
        }

        return max;
    }

    void dfs(int[][] a, int i, int j) {
        max = Math.max(j, max);
        if (max == a[0].length - 1)
            return;

        for (int k = Math.max(0, i - 1); k < Math.min(i + 2, a.length); k++) {
            if (a[k][j + 1] > a[i][j]) {
                dfs(a, k, j + 1);
            }
        }

        a[i][j] = 0;
    }
}