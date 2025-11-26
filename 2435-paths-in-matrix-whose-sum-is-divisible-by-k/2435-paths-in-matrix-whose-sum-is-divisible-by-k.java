/**
    https://leetcode.com/u/mfroo
 */
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numberOfPaths(int[][] grid, int k) {
        return rolling(grid, k);
    }

    private int rolling(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] prev = new int[n][k];
        int[][] curr = new int[n][k];

        int tmp = 0;
        for(int i = 0; i < n; i++) {
            tmp += grid[0][i];
            prev[i][tmp % k] = 1;
        }

        tmp = grid[0][0];

        for (int i = 1; i < m; i++) {
            tmp += grid[i][0];

            Arrays.fill(curr[0], 0); //reset base case - 0th column, otherwise prev base would carry over
                                            //no need to reset anything else - every cell will be written to by the main loop
                                            //and no cell will be read from before it's updated

            curr[0][tmp % k] = 1; //and set the base for current row

            for (int j = 1; j < n; j++) {
                int val = grid[i][j];

                for (int prevRem = 0; prevRem < k; prevRem++) {
                    int currRem = (prevRem + val) % k;

                    curr[j][currRem] = (int) ((long) prev[j][prevRem] + curr[j - 1][prevRem]) % MOD;
                }
            }

            int[][] tmpArr = prev;
            prev = curr;
            curr = tmpArr;
        }

        return prev[n - 1][0];
    }

    private int tabulation(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m * n][k]; //i * j compression

        int tmp = 0;
        for(int i = 0; i < n; i++) { //going right - first row essentially, they all have only one way to get to them
            tmp += grid[0][i];
            dp[i][tmp % k] = 1;
        }

        tmp = grid[0][0];
        for(int i = 1; i < m; i++) { //going down - first column, all these cells have just one way to get to them too
            tmp += grid[i][0];
            dp[i * n][tmp % k] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int val = grid[i][j];
                int coord = i * n + j;

                for (int prevRem = 0; prevRem < k; prevRem++) {
                    int currRem = (prevRem + val) % k;

                    dp[coord][currRem] = (int) ((long) dp[(i - 1) * n + j][prevRem] + dp[i * n + j - 1][prevRem]) % MOD; //safe not to accumulate here as base is set outside of the main loop
                }
            }
        }

        return dp[(m - 1) * n + n - 1][0];
    }

    private int memoization(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] cache = new int[m * n][k]; //memo[i * n + j][rem] = number of paths from cell[i][j] that have remainder of rem
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }

        return memoization(grid, k, 0, 0, 0, cache);
    }

    private int memoization(int[][] grid, int k, int i, int j, int remainder, int[][] cache) {
        int coord = i * grid[0].length + j;

        int currRemainder = (remainder + grid[i][j]) % k;

        if (cache[coord][currRemainder] != -1) {
            return cache[coord][currRemainder];
        }

        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return currRemainder == 0 ? 1 : 0;
        }

        return cache[coord][currRemainder] = ((i + 1 < grid.length ? memoization(grid, k, i + 1, j, currRemainder, cache) : 0) +
                (j + 1 < grid[0].length ? memoization(grid, k, i, j + 1, currRemainder, cache) : 0)) % MOD;
    }
}