class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n < 3) return -1;

        int maxNum = nums[0];
        for (int x : nums) {
            if (x > maxNum) maxNum = x;
        }
        int maxV = maxNum + n;
        final long INF = (long) 4e18;

        long[][] L = new long[n][maxV + 1];
        long[][] R = new long[n][maxV + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(L[i], INF);
            Arrays.fill(R[i], INF);
        }

        for (int v = 1; v <= maxV; ++v) {
            L[0][v] = Math.abs((long) v - (long) nums[0]);
        }
        for (int i = 1; i < n; ++i) {
            long prefMin = INF;
            for (int v = 1; v <= maxV; ++v) {
                if (prefMin < INF) {
                    L[i][v] = Math.abs((long) v - (long) nums[i]) + prefMin;
                }
                if (L[i - 1][v] < prefMin) {
                    prefMin = L[i - 1][v];
                }
            }
        }

        for (int v = 1; v <= maxV; ++v) {
            R[n - 1][v] = Math.abs((long) v - (long) nums[n - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            long prefMin = INF;
            for (int v = 1; v <= maxV; ++v) {
                if (prefMin < INF) {
                    R[i][v] = Math.abs((long) v - (long) nums[i]) + prefMin;
                }
                if (R[i + 1][v] < prefMin) {
                    prefMin = R[i + 1][v];
                }
            }
        }

        long ans = INF;
        for (int p = 1; p < n - 1; ++p) {
            for (int v = 1; v <= maxV; ++v) {
                if (L[p][v] < INF && R[p][v] < INF) {
                    long total = L[p][v] + R[p][v] - Math.abs((long) v - (long) nums[p]);
                    if (total < ans) {
                        ans = total;
                    }
                }
            }
        }

        return ans >= INF ? -1 : (int) ans;
    }
}