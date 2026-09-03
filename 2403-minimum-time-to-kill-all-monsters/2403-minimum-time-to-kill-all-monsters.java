class Solution {
    public long minimumTime(int[] power) {

        int n = power.length;

        int fullMask = (1<<n);

        long[] dp = new long[fullMask];

        Arrays.fill(dp,Long.MAX_VALUE);

        dp[0] = 0L;


        for(int mask=1;mask<fullMask;mask++) {

            int count = Integer.bitCount(mask);

            for(int i=0;i<n;i++) {

                if((mask & (1<<i)) != 0) {

                    dp[mask] = Math.min(dp[mask],dp[mask ^ (1<<i)] + (power[i] + count - 1)/count );
                }
            }
        }

        return dp[fullMask-1];
        
    }
}