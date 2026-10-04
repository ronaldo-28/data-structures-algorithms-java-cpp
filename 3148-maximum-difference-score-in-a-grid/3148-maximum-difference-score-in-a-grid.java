class Solution {
    // largest value bottom right of an index that is not
    public int maxScore(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size(), res = Integer.MIN_VALUE;
        int[] dp = new int[n];
        dp[n - 1] = -1_000_000;
        for (int i = m - 1; i >= 0; i--) {
            List<Integer> list = grid.get(i);
            int rowMax = list.get(n - 1);
            res = Math.max(res, dp[n - 1] - rowMax);
            dp[n - 1] = Math.max(dp[n - 1], rowMax);
            for (int j = n - 2; j >= 0; j--) {
                int x = list.get(j);
                res = Math.max(res, Math.max(dp[j], rowMax) - x);
                rowMax = Math.max(rowMax, x);
                dp[j] = Math.max(dp[j], rowMax);
            }
        }
        return res;
    }
}