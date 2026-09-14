class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int maxLaps = 20;
        long[] best = new long[maxLaps + 1];
        Arrays.fill(best, Long.MAX_VALUE);

        for (int[] t : tires) {
            long f = t[0], r = t[1];
            long time = f, total = f;

            for (int k = 1; k <= maxLaps; k++) {
                best[k] = Math.min(best[k], total);
                time *= r;
                if (time > f + changeTime) break;
                total += time;
            }
        }

        long[] dp = new long[numLaps + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= numLaps; i++) {
            for (int k = 1; k <= maxLaps && k <= i; k++) {
                if (best[k] == Long.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i], dp[i - k] + best[k] + changeTime);
            }
        }

        return (int)(dp[numLaps] - changeTime);
    }
}