class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        return new Result().maxConsistentColumns(grid, limit);
    }
}
class Result {
    static int maxConsistentColumns(int[][] g, int L) {
        int m = g.length, n = g[0].length;
        int[][] c = new int[n][m];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) c[j][i] = g[i][j];
        int[] dp = new int[n];
        int ans = 1;
        for (int b = 0; b < n; b++) {
            dp[b] = 1;
            for (int a = b - 1; a >= 0; a--) {
                if (dp[a] < dp[b]) continue;
                int[] u = c[a], v = c[b];
                int i = 0;
                while (i < m) { int d = v[i] - u[i]; if (d > L || d < -L) break; i++; }
                if (i == m) dp[b] = dp[a] + 1;
            }
            if (dp[b] > ans) ans = dp[b];
        }
        return ans;
    }
    public static void main(String[] args) {
        chk(maxConsistentColumns(new int[][]{{-2,0,3}}, 2), 2);
        chk(maxConsistentColumns(new int[][]{{1,-1,1},{2,2,2}}, 1), 2);
        chk(maxConsistentColumns(new int[][]{{-5,5}}, 9), 1);
    }
    static void chk(int g, int x) { System.out.println((g == x ? "PASS " : "FAIL ") + g + " expected " + x); }
}