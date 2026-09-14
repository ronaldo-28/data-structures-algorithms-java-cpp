class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[] dp = new double[query_row + 1];
        dp[0] = (double)poured;
        for (int i = 0; i < dp.length - 1; i++) {
            for (int j = i; j > -1; j--) {
                if (dp[j] > 1) {
                    double overflow = (dp[j] - 1) / 2;
                    dp[j] = overflow;
                    dp[j + 1] += overflow;
                } else {
                    dp[j] = 0;
                }
            }
        }
        return Math.min(1, dp[query_glass]);
    }
}