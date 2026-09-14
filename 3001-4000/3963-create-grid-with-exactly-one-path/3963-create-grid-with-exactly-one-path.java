class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(grid[i], '#');
        }

        for (int i = 0; i < n; i++) {
            grid[0][i] = '.';
        }

        for (int i = 1; i < m; i++) {
            grid[i][n - 1] = '.';
        }

        String[] res = new String[m];
        for (int i = 0; i < m; i++) {
            res[i] = new String(grid[i]);
        }

        return res;
    }
}