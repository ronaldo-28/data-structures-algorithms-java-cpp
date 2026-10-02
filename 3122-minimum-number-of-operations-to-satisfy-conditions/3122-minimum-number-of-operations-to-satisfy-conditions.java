class Solution {
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] count = new int[n][10];
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                count[j][row[j]]++;
            }
        }

        int[][] dp = new int[n][10];
        for (int x = 0; x < 10; x++) {
            dp[0][x] = m - count[0][x];
        }
        for (int j = 1; j < n; j++) {
            // find min dp[j - 1][x] and 2nd min
            int minx = -1, minx2 = -1;
            int mincnt = Integer.MAX_VALUE, mincnt2 = Integer.MAX_VALUE;
            for (int x = 0; x < 10; x++) {
                int c = dp[j - 1][x];
                if (c < mincnt) {
                    mincnt2 = mincnt;
                    minx2 = minx;
                    mincnt = c;
                    minx = x;
                } else if (c < mincnt2) {
                    mincnt2 = c;
                    minx2 = x;
                }
            }
            for (int x = 0; x < 10; x++) {
                int prev = minx != x ? mincnt : mincnt2;
                dp[j][x] = (m - count[j][x]) + prev;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int v : dp[n - 1]) min = Math.min(min, v);
        return min;
    }
}