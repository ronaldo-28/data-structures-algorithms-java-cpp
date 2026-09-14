class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] dp = new int[n];
        for (int i = n - 2; i > -1; --i) {
            int total = stones[i], s = total, p = 0;
            for (int j = i + 1; j < n; ++j) {
                int left = total - p;
                total += stones[j];
                int right = total - s - dp[j];
                dp[j] = p = Math.max(left, right);
            }
        }
        return dp[n - 1];
    }
}