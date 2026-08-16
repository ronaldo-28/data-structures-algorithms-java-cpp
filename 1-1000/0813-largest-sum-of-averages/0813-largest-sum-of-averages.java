import java.util.Arrays;

class Solution {
    private double[][] memo;
    private double[] pref;
    private int n;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        pref = new double[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];

        memo = new double[n][k + 1];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1.0);

        return dfs(0, k);
    }

    private double dfs(int i, int k) {
        if (memo[i][k] >= 0) return memo[i][k];

        // If only one group left, take average of remaining
        if (k == 1) return memo[i][k] = avg(i, n - 1);

        double best = 0.0;

        // End first group at j, leave at least (k-1) elements for remaining groups
        for (int j = i; j <= n - k; j++) {
            double first = avg(i, j);
            double rest = dfs(j + 1, k - 1);
            best = Math.max(best, first + rest);
        }

        return memo[i][k] = best;
    }

    private double avg(int l, int r) {
        return (pref[r + 1] - pref[l]) / (r - l + 1);
    }
}