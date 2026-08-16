class Solution {
    int n, m;
    Integer[][] dp;
    public int minimizeTheDifference(int[][] mat, int target) {
        n = mat.length;
        m = mat[0].length;
        dp = new Integer[n][5001];
        for(int[] x : mat) Arrays.sort(x);
        return calc(mat, 0, target, 0);
    }
    private int calc(int[][] mat, int index, int target, int current) {
        if(index == n) return Math.abs(target - current);
        if(dp[index][current] != null) return dp[index][current];
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            min = Math.min(min, calc(mat, index + 1, target, current + mat[index][i]));
            if(min == 0 || current + mat[index][i] >= target) break;
        }
        return dp[index][current] = min;
    }
}