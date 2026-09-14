class Solution {
    static{
        for(int i = 0; i < 200; i++){
            mostPoints(new int[][]{{0,0}});
        }
    }
    public static long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n+1];

        for(int i = n-1; i >= 0; i--){
            int point = questions[i][0], idx = questions[i][1];

            dp[i] = point;
            if(i < n - idx){
                dp[i] += dp[i + idx + 1];
            }

            dp[i] = Math.max(dp[i], dp[i+1]);
        }

        return dp[0];
    }
}