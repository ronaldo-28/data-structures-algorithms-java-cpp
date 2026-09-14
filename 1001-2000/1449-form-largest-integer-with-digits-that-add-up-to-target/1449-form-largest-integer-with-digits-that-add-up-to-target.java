class Solution {
    public String largestNumber(int[] cost, int target) {
        // dp[t] = max number of digits achievable with total cost t
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) dp[i] = -10000;
        dp[0] = 0;

        for (int d = 1; d <= 9; d++) {
            int c = cost[d - 1];
            for (int t = c; t <= target; t++) {
                if (dp[t - c] + 1 > dp[t]) {
                    dp[t] = dp[t - c] + 1;
                }
            }
        }

        if (dp[target] < 0) return "0";

        StringBuilder sb = new StringBuilder();
        int t = target;
        // build lexicographically largest by trying bigger digits first
        for (int d = 9; d >= 1; d--) {
            int c = cost[d - 1];
            while (t >= c && dp[t] == dp[t - c] + 1) {
                sb.append(d);
                t -= c;
            }
        }
        return sb.toString();
    }
}