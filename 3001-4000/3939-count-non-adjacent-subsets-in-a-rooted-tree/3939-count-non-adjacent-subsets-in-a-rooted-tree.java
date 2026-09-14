class Solution {
    public int countValidSubsets(int[] parent, int[] nums, int k) {
        int n = parent.length;
        long MOD = 1000000007;

        long [][][] dp = new long[n][2][k];

        for (int i = 0; i<n; i++){
            dp[i][0][0] = 1;
            int rem = nums[i] % k;
            dp[i][1][rem] = 1;
        }

        long[] childWays = new long[k];
        for (int i = n -1; i>0; i--) {
            int p = parent[i];
            long[] p0 = dp[p][0];
            long[] p1 = dp[p][1];
            long[] c0 = dp[i][0];
            long[] c1 = dp[i][1];

            for (int r = 0; r<k; r++) {
                long sum = c0[r] + c1[r];
                if (sum >= MOD) sum -= MOD;
                childWays[r] = sum;
            }

            long[] nextP0 =new long[k];
            long[] nextP1 = new long[k];

            for (int r1 = 0; r1 <k ; r1++){
                long v0 = p0[r1];
                long v1 = p1[r1];

                if (v0 >0) {
                    for (int r2 = 0; r2 <k; r2++) {
                        long w= childWays[r2];
                        if (w>0) {
                            int rem = r1 + r2;
                            if (rem >= k) rem -= k;
                            nextP0[rem] = (nextP0[rem] + v0 *w) % MOD;
                        }
                    }
                }

                if (v1 >0){
                    for (int r2 = 0; r2 < k; r2++) {
                        long w= c0[r2];
                        if (w>0) {
                            int rem = r1 +r2;
                            if (rem >= k) rem -= k;
                            nextP1[rem] = (nextP1[rem] + v1 * w) % MOD;
                        }
                    }
                }
            }

            dp[p][0] = nextP0;
            dp[p][1] = nextP1;
        }

        long ans = (dp[0][0][0] + dp[0][1][0] - 1 + MOD) % MOD;
        return (int) ans;
    }
}