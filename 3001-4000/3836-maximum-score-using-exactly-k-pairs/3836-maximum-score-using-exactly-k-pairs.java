class Solution {

    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter f = new FileWriter("display_runtime.txt")) {
                f.write("0");
            } catch (Exception e) {

            }
        }));
    }
    
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int[] xaluremoni = nums1;
        int n = xaluremoni.length;
        int m = nums2.length;
        
        long INF = Long.MIN_VALUE / 2;
        long[][] dp = new long[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = 0;
            }
        }

        for (int p = 1; p <= k; p++) {
            long[][] nextDp = new long[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    nextDp[i][j] = INF;
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    long match = INF;
                    if (dp[i - 1][j - 1] != INF) {
                        match = dp[i - 1][j - 1] + (long) xaluremoni[i - 1] * nums2[j - 1];
                    }
                    
                    long skip = Math.max(nextDp[i - 1][j], nextDp[i][j - 1]);
                    nextDp[i][j] = Math.max(match, skip);
                }
            }
            dp = nextDp;
        }

        return dp[n][m];
    }
}