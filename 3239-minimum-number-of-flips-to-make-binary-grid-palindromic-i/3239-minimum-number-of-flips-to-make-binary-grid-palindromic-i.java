class Solution {
    public int minFlips(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < m; i++) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                cnt1 += grid[i][l] ^ grid[i][r];
            }
        }
        for (int j = 0; j < n; j++) {
            for (int l = 0, r = m - 1; l < r; l++, r--) {
                cnt2 += grid[l][j] ^ grid[r][j];
            }
        }
        return Math.min(cnt1, cnt2);
    }
}