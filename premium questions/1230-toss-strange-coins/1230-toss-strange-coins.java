class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];

        dp[0] = 1;

        for (int i = prob.length - 1; i >= 0; --i) {
            for (int j = Math.min(prob.length - i, target); j >= 0; --j) {
                dp[j] = prob[i] * (j == 0 ? 0 : dp[j - 1]) + (1 - prob[i]) * dp[j];
            }
        }
        return dp[target];
    }
}