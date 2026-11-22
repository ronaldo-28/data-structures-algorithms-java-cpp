class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n < 3) return -1;

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int V = max + n, INF = 1000000000;
        int[][] L = new int[n][V];
        int[][] R = new int[n][V];

        for (int j = 0; j < V; j++) {
            L[0][j] = Math.abs(nums[0] - (j + 1));
            R[n - 1][j] = Math.abs(nums[n - 1] - (j + 1));
        }

        for (int i = 1; i < n; i++) {
            int best = INF;
            for (int j = 0; j < V; j++) {
                if (j > 0) best = Math.min(best, L[i - 1][j - 1]);
                if (best < INF)
                    L[i][j] = best + Math.abs(nums[i] - (j + 1));
                else
                    L[i][j] = INF;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            int best = INF;
            for (int j = 0; j < V; j++) {
                if (j > 0) best = Math.min(best, R[i + 1][j - 1]);
                if (best < INF)
                    R[i][j] = best + Math.abs(nums[i] - (j + 1));
                else
                    R[i][j] = INF;
            }
        }

        int ans = INF;
        for (int i = 1; i < n - 1; i++)
            for (int j = 0; j < V; j++)
                ans = Math.min(ans, L[i][j] + R[i][j]
                        - Math.abs(nums[i] - (j + 1)));

        return ans;
    }
}